package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.dao.RoleDao;
import ua.com.epam.library.entity.Role;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.RoleService;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {

    private static final Logger log = LogManager.getLogger(RoleServiceImpl.class.getName());

    private final RoleDao roleDao;

    public RoleServiceImpl() {
        this(DaoFactory.getInstance().getRoleDao());
    }

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Optional<Role> retrieveRoleById(long roleId) {
        log.info("Searching role by role id: {}", roleId);
        return roleDao.findById(roleId);
    }

    @Override
    public Optional<Role> retrieveRoleByName(String role) throws ServiceException {
        log.info("Searching role by role name: {}", role);
        return roleDao.findByName(role);
    }
}
