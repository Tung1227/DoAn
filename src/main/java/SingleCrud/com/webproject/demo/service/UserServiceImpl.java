package SingleCrud.com.webproject.demo.service;


import SingleCrud.com.webproject.demo.model.User;
import SingleCrud.com.webproject.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByName(String account) {
        List<User> users = this.findAll();
        for (int i = 0 ; i < users.size(); i++) {
            if(users.get(i).getTaiKhoan().equals(account)) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public User findById(String id) {
        List<User> users = this.findAll();
        for (int i = 0 ; i < users.size(); i++) {
            if(users.get(i).getID().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public User login(User user) {
        List<User> users = this.findAll();
        List<String> acc = new ArrayList<String>();
        for (int i = 0 ; i < users.size(); i++) {
            acc.add(users.get(i).getTaiKhoan());
        }

        int index =  acc.indexOf(user.getTaiKhoan());
        if(index != - 1) {
            if (!user.getMatKhau().equals(users.get(index).getMatKhau())) {
//                System.out.println("nahhh!!!");
                return null;
            } else {
                return users.get(index);
            }
        }
        return null;

    }

    @Override
    public User updatePass(User user, String newPass) {
        user.setMatKhau(newPass);
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(User user, User newUser) {
        user.setTen(newUser.getTen());
        user.setNgaySinh(newUser.getNgaySinh());
        user.setDiaChi(newUser.getDiaChi());
        user.setSDT(newUser.getSDT());
        user.setGioiTinh(newUser.getGioiTinh());
        user.setGmail(newUser.getGmail());
        user.setRole(newUser.getRole());
        userRepository.save(user);
        return user;
    }

    @Override
    public User addUser(User user) {
        User newUser = new User(user.getTaiKhoan(), user.getMatKhau(), user.getTen(), user.getNgaySinh(), user.getGioiTinh(), user.getGmail(), user.getSDT(), user.getDiaChi(), user.getRole());
//        System.out.println(newUser);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User updateStatus(User user, String status) {
        user.setTrangThai(status);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> search(String truong, String giatri) {
        List<User> userList = this.findAll();
        List<User> searchList = new ArrayList<User>();
        if (giatri.equals("")) {
            return userList;
        }
        if (truong.equals("Ten")) {
            for (int i = 0 ; i < userList.size(); i++) {
                if (userList.get(i).getTen().toLowerCase().contains(giatri.toLowerCase())) {
                    searchList.add(userList.get(i));
                }
            }
            return searchList;
        } else if (truong.equals("DiaChi")) {
            for (int i = 0 ; i < userList.size(); i++) {
                if (userList.get(i).getDiaChi().toLowerCase().contains(giatri.toLowerCase())) {
                    searchList.add(userList.get(i));
                }
            }
            return searchList;
        }
//        else  if (truong.equals("TrangThai")) {
//            for (int i = 0 ; i < userList.size(); i++) {
//                if (userList.get(i).getTrangThai().toLowerCase().contains(giatri.toLowerCase())) {
//                    searchList.add(userList.get(i));
//                }
//            }
//            return searchList;
//        }
        return null;
    }


}
