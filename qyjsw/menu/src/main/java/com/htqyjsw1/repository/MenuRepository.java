package com.htqyjsw1.repository;

import com.htqyjsw1.entity.Menu;
import java.util.List;

public interface MenuRepository {
  public int count();
//  public Menu findAll();
 // public double max_menu_pric();
  public List<Menu> findAll();
  public com.htqyjsw1.entity.Menu findById(long menu_id);
}
