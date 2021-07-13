package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final PhanQuyenImpl phanQuyen;

    @Autowired
    public MyUserDetailsService(UserService userService, PhanQuyenImpl phanQuyen) {
        this.userService = userService;
        this.phanQuyen = phanQuyen;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userService.findByName(account);
        if (user == null) {
            System.out.println("User not found !" + account);
            throw new UsernameNotFoundException("User " + account + "was not found in DB");
        }
        String roleName = this.phanQuyen.findById(user.getRole()).getLoaiTaiKhoan();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        grantList.add(authority);
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getTaiKhoan(), user.getMatKhau(), grantList);
        return userDetails;
    }
}
