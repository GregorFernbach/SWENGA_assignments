package at.fh.swenga.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.EmployeeManager;
import at.fh.swenga.model.EmployeeModel;

@Controller
public class EmployeeManagerController {

	@Autowired
	private EmployeeManager employeeManager;

	/**
	 * Before the controller is used the first time -> add a custom editor for
	 * handling Date objects
	 * 
	 * @param binder
	 */
	/**
	 * @InitBinder public void initBinder(WebDataBinder binder) { SimpleDateFormat
	 *             dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	 *             dateFormat.setLenient(false);
	 *             binder.registerCustomEditor(Date.class, new
	 *             CustomDateEditor(dateFormat, false)); }
	 */

	@RequestMapping(value = { "/", "listEmployees" })
	public String showAllEmployees(Model model) {
		model.addAttribute("employees", employeeManager.getAllEmployees());
		return "listEmployees";
	}

	@RequestMapping("/fillEmployeeList")
	public String fillEmployeeList(Model model) {

		// Calendar now = Calendar.getInstance();
		Date now = new Date();
		employeeManager.addEmployee(new EmployeeModel(1, "Max", "Mustermann", 3500, now));
		employeeManager.addEmployee(new EmployeeModel(2, "Mario", "Rossi", 2800, now));
		employeeManager.addEmployee(new EmployeeModel(3, "John", "Doe", 2900, now));
		employeeManager.addEmployee(new EmployeeModel(4, "Jane", "Doe", 788, now));
		employeeManager.addEmployee(new EmployeeModel(5, "Maria", "Noname", 9999999, now));
		employeeManager.addEmployee(new EmployeeModel(6, "Josef", "Noname", 2, now));
		employeeManager.addEmployee(new EmployeeModel(7, "Gregor", "Fernbach", 6000, now));

		model.addAttribute("employees", employeeManager.getAllEmployees());
		return "listEmployees";
	}

	@RequestMapping("/deleteEmployee")
	public String delete(Model model, @RequestParam int ssn) {
		boolean isRemoved = employeeManager.remove(ssn);

		if (isRemoved) {
			model.addAttribute("warningMessage", "Employee " + ssn + " deleted");
		} else {
			model.addAttribute("errorMessage", "There is no Employee " + ssn);
		}

		// Multiple ways to "forward" to another Method
		// return "forward:/listEmployees";
		return showAllEmployees(model);
	}

	@RequestMapping("/searchEmployees")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("employees", employeeManager.getFilteredEmployees(searchString));
		return "listEmployees";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String showAddEmployeeForm(Model model) {
		return "editEmployee";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@Valid @ModelAttribute EmployeeModel newEmployeeModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listEmployees";
		}

		EmployeeModel employee = employeeManager.getEmployeeBySSN(newEmployeeModel.getSsn());

		if (employee != null) {
			model.addAttribute("errorMessage", "Employee already exists!<br>");
		} else {
			employeeManager.addEmployee(newEmployeeModel);
			model.addAttribute("message", "New employee " + newEmployeeModel.getSsn() + " added.");
		}

		return "forward:/listEmployees";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public String showChangeEmployeeForm(Model model, @RequestParam int ssn) {
		EmployeeModel employee = employeeManager.getEmployeeBySSN(ssn);
		if (employee != null) {
			model.addAttribute("employee", employee);
			return "editEmployee";
		} else {
			model.addAttribute("errorMessage", "Couldn't find employee " + ssn);
			return "forward:/listEmployees";
		}
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editEmployee", method = RequestMethod.POST)
	public String changeEmployee(@Valid @ModelAttribute EmployeeModel changedEmployeeModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listEmployees";
		}

		EmployeeModel employee = employeeManager.getEmployeeBySSN(changedEmployeeModel.getSsn());

		if (employee == null) {
			model.addAttribute("errorMessage", "Employee does not exist!<br>");
		} else {
			employee.setSsn(changedEmployeeModel.getSsn());
			employee.setFirstName(changedEmployeeModel.getFirstName());
			employee.setLastName(changedEmployeeModel.getLastName());
			employee.setSalary(changedEmployeeModel.getSalary());
			employee.setDayOfBirth(changedEmployeeModel.getDayOfBirth());
			model.addAttribute("message", "Changed employee " + changedEmployeeModel.getSsn());
		}

		return "forward:/listEmployees";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}
}
