package rikkei.academy.service.role;

import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;

public interface IRoleService {
    Role findByName(RoleName name);
    Role findById(int id);
}
