package com.geomhwein.go.securlty;

import com.geomhwein.go.command.UserAuthVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAuth implements UserDetails {


	private final UserAuthVO userAuthVO;

	public UserAuth(UserAuthVO userAuthVO) {
		this.userAuthVO = userAuthVO;
	}

	public String getRole() {
		return userAuthVO.getUserDetailsVO().getUserRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		 List<GrantedAuthority> list = new ArrayList<>();

		list.add( () -> userAuthVO.getUserDetailsVO().getUserRole() );

		return list;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_" + userAuthVO.getUserDetailsVO().getUserRole()));
//		return authorities;
//	}

	@Override
	public String getPassword() {
		return userAuthVO.getUserPwHash();
	}

	@Override
	public String getUsername() {
		return userAuthVO.getUserNm();
	}

	public String getUserId() {
		return userAuthVO.getUserId(); }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
