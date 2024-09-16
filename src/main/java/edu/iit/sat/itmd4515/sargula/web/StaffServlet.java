/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Staff;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author sargula
 */
@WebServlet(name = "", urlPatterns = {"/staff", "/s"})
public class StaffServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(StaffServlet.class.getName());

    @Resource
    Validator validator;

    @Resource(name = "java:app/jdbc/itmd4515DS")
    DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Inside StaffServlet.doGet()");
        resp.sendRedirect(req.getContextPath() + "/staff.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Inside StaffServlet.doPost()");

        String staffIdParam = req.getParameter("staffId");
        String staffFirstNameParam = req.getParameter("firstName");
        String staffLastNameParam = req.getParameter("lastName");
        String staffAddressIdParam = req.getParameter("addressId");
        String staffEmailParam = req.getParameter("email");
        String staffStoreIdParam = req.getParameter("storeId");
        String staffActiveIndParam = req.getParameter("activeInd");
        String staffUsernameParam = req.getParameter("username");
        String staffPasswordParam = req.getParameter("password");
        String staffLastUpdateParam = req.getParameter("lastUpdate");

        LOG.info("staffIdParam:\t\t\t\t" + staffIdParam);
        LOG.info("staffFirstNameParam:\t\t\t\t" + staffFirstNameParam);
        LOG.info("staffLastNameParam:\t\t\t\t" + staffLastNameParam);
        LOG.info("staffEmailParam:\t\t\t\t" + staffEmailParam);
        LOG.info("staffAddressIdParam:\t\t\t\t" + staffAddressIdParam);
        LOG.info("staffActiveIndParam:\t\t\t\t" + staffActiveIndParam);
        LOG.info("staffStoreIdParam:\t\t\t\t" + staffStoreIdParam);
        LOG.info("staffLastUpdateParam:\t\t\t\t" + staffLastUpdateParam);

        Staff staff = new Staff();

        if (staffIdParam != null && !staffIdParam.isBlank()) {
            staff.setId(Integer.valueOf(staffIdParam));
        }

        if (staffFirstNameParam != null && !staffFirstNameParam.isBlank()) {
            staff.setFirstName(staffFirstNameParam);
        }

        if (staffLastNameParam != null && !staffLastNameParam.isBlank()) {
            staff.setLastName(staffLastNameParam);
        }

        if (staffAddressIdParam != null && !staffAddressIdParam.isBlank()) {
            staff.setAddressId(Integer.valueOf(staffAddressIdParam));
        }

        if (staffEmailParam != null && !staffEmailParam.isBlank()) {
            staff.setEmail(staffEmailParam);
        }

        if (staffStoreIdParam != null && !staffStoreIdParam.isBlank()) {
            staff.setStoreId(Integer.valueOf(staffStoreIdParam));
        }

        if (staffActiveIndParam != null && !staffActiveIndParam.isBlank()) {
            if (staffActiveIndParam.equalsIgnoreCase("ON")) {
                staff.setActive(true);
            } else {
                staff.setActive(false);
            }
        } else {
            staff.setActive(false);
        }

        if (staffUsernameParam != null && !staffUsernameParam.isBlank()) {
            staff.setUsername(staffUsernameParam);
        }

        if (staffPasswordParam != null && !staffPasswordParam.isBlank()) {
            staff.setPassword(staffPasswordParam);
        }

        if (staffLastUpdateParam != null && !staffLastUpdateParam.isBlank()) {
            staff.setLastUpdate(LocalDate.parse(staffLastUpdateParam));
        }

        LOG.info("Built Staff: " + staff.toString());

        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);

        if (violations.size() > 0) {
            // FAILED Validation
            LOG.info("Staff has failed validation");
            for (ConstraintViolation<Staff> violation : violations) {
                LOG.info(violation.getPropertyPath() + " " + violation.getMessage());
            }

            req.setAttribute("staff", staff);
            req.setAttribute("violations", violations);

            RequestDispatcher rd = req.getRequestDispatcher("staff.jsp");
            rd.forward(req, resp);

        } else {
            // PASSED Validation
            LOG.info("Staff has passed validation");

            try {
                createStaff(staff);
            } catch (SQLException ex) {
                Logger.getLogger(StaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            req.setAttribute("staff", staff);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/conf.jsp");
            rd.forward(req, resp);

        }
    }

    private void createStaff(Staff staff) throws SQLException {
        String insertStaff = "insert into staff "
                + "(staff_id, first_name, last_name, address_id, email, "
                + "store_id, active, username, password, last_update) "
                + "values (?,?,?,?,?,?,?,?,?,?)";

        try (
                Connection c = ds.getConnection(); PreparedStatement ps = c.prepareCall(insertStaff)) {

            ps.setInt(1, staff.getId());
            ps.setString(2, staff.getFirstName());
            ps.setString(3, staff.getLastName());
            ps.setInt(4, staff.getAddressId());
            ps.setString(5, staff.getEmail());
            ps.setInt(6, staff.getStoreId());
            ps.setBoolean(7, staff.getActive());
            ps.setString(8, staff.getUsername());
            ps.setString(9, staff.getPassword());
            ps.setObject(10, staff.getLastUpdate());

            ps.executeUpdate();
        }
    }
}
