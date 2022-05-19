package assetmanagement.asset.controller;

import assetmanagement.asset.entity.Staff;
import assetmanagement.asset.services.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class WebController {

	@Autowired
	WebService webService;

	@GetMapping("/")
	public String index(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("userLoggedIn") != null && httpSession.getAttribute("userLoggedIn").equals("yes")) {
			return "redirect:/home";
		}
		model.addAttribute("applicationName", "Asset Management");
		return "index";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("applicationName", "Asset Management");
		return "register";
	}

	@PostMapping("/submit")
	public String submit(@Valid Staff staff, BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			model.addAttribute("errorMessage", binding);
			return "register";
		}
		webService.saveStaff(staff);
		return "redirect:/";

	}


	@PostMapping("/loginverify")
	public String loginverify(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession httpSession) {

		String response = webService.verifyStaff(email, password, httpSession);

		if (response.equals("success")) {
			return "redirect:/home";
		} else {
			model.addAttribute("response", response);

			return "index";
		}

	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession httpSession) {

		httpSession.invalidate();
		model.addAttribute("applicationName", "Asset Management");
		return "redirect:/";
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession httpSession) {

		if (httpSession.getAttribute("userLoggedIn") == null) {

			return "redirect:/";
		} else {
			if (!httpSession.getAttribute("userLoggedIn").equals("yes")) {
				return "redirect:/";

			}
		}

		model.addAttribute("name", httpSession.getAttribute("userName"));
		model.addAttribute("email", httpSession.getAttribute("userEmail"));
		model.addAttribute("mobile", httpSession.getAttribute("userMobile"));
		return "home";
	}


}
