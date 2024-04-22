package com.geomhwein.go.securlty;

import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.service.NormalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private NormalUserMapper normalUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("사용자가 로그인을 시도함");
		System.out.println("사용자가 입력한아이디:" + username);

		UserAuthVO userAuthVO = normalUserMapper.signIn(username);

		if(userAuthVO != null) {
			return new UserAuth(userAuthVO);
		}

		return null;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		System.out.println("사용자가 로그인을 시도함");
//		System.out.println("사용자가 입력한아이디:" + username);
//		UserAuthVO userAuthVO = normalUserMapper.signIn(username); // DB에서 사용자 정보를 불러옴
//
//		if (userAuthVO == null) {
//			throw new UsernameNotFoundException("User not found");
//		}
//
//		// UserDetailsVO 안에 있는 권한 정보를 가져와서 GrantedAuthority 목록을 생성
//		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		grantedAuthorities.add(new SimpleGrantedAuthority(userAuthVO.getUserDetailsVO().getUserRole()));
//
//		// UserDetails 객체를 생성하여 반환
//		return new User(userAuthVO.getUserId(), userAuthVO.getUserPwHash(), grantedAuthorities);
//	}

}
