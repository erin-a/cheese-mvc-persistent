package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */

//TO DOne 1.2 Setting Up the New DAO: done?

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
