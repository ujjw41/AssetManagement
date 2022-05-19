package assetmanagement.asset.services;

import assetmanagement.asset.entity.Staff;
import assetmanagement.asset.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class WebService {
    @Autowired
    StaffRepo staffRepo;

    public void saveStaff(Staff staff) {
        staffRepo.save(staff);
    }

    public String verifyStaff(String email, String password, HttpSession httpSession) {

        List<Staff> sStaff = staffRepo.findByEmail(email);
        if (sStaff.size() ==0){
            return "user not registered";
        }
        if (sStaff.get(0).getPassword().equals(password)){
            httpSession.setAttribute("userLoggedIn", "yes");
            httpSession.setAttribute("userName", sStaff.get(0).getName());
            httpSession.setAttribute("userMobile", sStaff.get(0).getMobile());
            httpSession.setAttribute("userEmail", sStaff.get(0).getEmail());
            return "success";
        } else {
            return "invalid password";
        }

    }
}
