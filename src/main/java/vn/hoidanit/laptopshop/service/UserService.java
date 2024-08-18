package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    
    

    public String handleHello(){
        return "Hello from service";
    }
    public User GetUserByID(Long id){
        return this.userRepository.findByid(id);
    }
    public List<User> GetAllUser(){
        return this.userRepository.findAll();
    }
    public List<User> GetAllUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    public User handleSaveUser(User user){
        return this.userRepository.save(user);
    }
    public void handleDeleteUserById(Long id){
        this.userRepository.deleteById(id);
    }
    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }
    public User RegisterDTOtoUser(RegisterDTO registerDTO){
        User user=new User();
        user.setFullName(registerDTO.getFirstName()+ " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }
}
