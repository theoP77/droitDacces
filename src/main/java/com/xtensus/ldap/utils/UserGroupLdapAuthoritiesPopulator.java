package com.xtensus.ldap.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.directory.SearchControls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
/**
 * This class adds the ability to provide a concrete map of associations between
 * specific group values and roles. These associations can be provided by
 * setting the <code>groupToRoleMap</code> property either in a Spring context
 * file or by calling {@link #setGroupToRoleMap(Map)}.
 */
public class UserGroupLdapAuthoritiesPopulator extends DefaultLdapAuthoritiesPopulator
		implements LdapAuthoritiesPopulator {

	private final Log logger = LogFactory.getLog(UserGroupLdapAuthoritiesPopulator.class);
	private final SearchControls searchControls = new SearchControls();
	private final SpringSecurityLdapTemplate ldapTemplate;
	/**
	 * Default value is <code>cn</code>.
	 */
	private String groupRoleAttribute = "cn";
	/**
	 * Default value is <code>(member={0})</code>.
	 */
	private String groupSearchFilter = "(member={0})";
	/**
	 * Map is empty by default.
	 */
	private Map<String, List<String>> groupToRoleMap = new HashMap<String, List<String>>();

	public UserGroupLdapAuthoritiesPopulator(ContextSource contextSource, String groupSearchBase) {
		super(contextSource, groupSearchBase);
		this.ldapTemplate = new SpringSecurityLdapTemplate(contextSource);
		this.ldapTemplate.setSearchControls(searchControls);
	}
	
	@Override
	protected Set<GrantedAuthority> getAdditionalRoles(final DirContextOperations user, final String username) {
		
		final String userDn = user.getNameInNamespace();
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		System.out.println("dans getAdditionalRoles");
		
		if (super.getGroupSearchBase() == null) {
			return authorities;
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Searching for roles for user '" + username + "', DN = " + "'" + userDn + "', with filter "
					+ this.groupSearchFilter + " in search base '" + super.getGroupSearchBase() + "'");
		}
		
		final Set<String> userRoles = ldapTemplate.searchForSingleAttributeValues(super.getGroupSearchBase(),
				this.groupSearchFilter, new String[] { userDn, username }, this.groupRoleAttribute);
			for (String group : userRoles) {
			final List<String> rolesForGroup = this.groupToRoleMap.get(group);
			logger.debug("Checking " + group + " for an associated role");
			if (rolesForGroup != null) {
				for (String role : rolesForGroup) {
					authorities.add(new SimpleGrantedAuthority(role));
					logger.debug("Added role: " + role + " based on group " + group);
				}
			}
		}

		return authorities;
	}

	@Override
	public void setGroupRoleAttribute(final String groupRoleAttribute) {
		super.setGroupRoleAttribute(groupRoleAttribute);
		this.groupRoleAttribute = groupRoleAttribute;
	}

	@Override
	public void setGroupSearchFilter(final String groupSearchFilter) {
		super.setGroupSearchFilter(groupSearchFilter);
		this.groupSearchFilter = groupSearchFilter;
	}

	/**
	 * 
	 * This function returns a list of roles from the given set of groups based on
	 * the value of the <code>groupToRoleMap</code> property.
	 * 
	 * @return a {@link java.util.Set} object.
	 */
	public void setGroupToRoleMap(final Map<String, List<String>> groupToRoleMap) {
		this.groupToRoleMap = groupToRoleMap;
	}

	@Override
	public void setSearchSubtree(boolean searchSubtree) {
		super.setSearchSubtree(searchSubtree);
		int searchScope = searchSubtree ? SearchControls.SUBTREE_SCOPE : SearchControls.ONELEVEL_SCOPE;
		this.searchControls.setSearchScope(searchScope);
	}
}