
package cn.superion.equipment.spare.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqUnitDict;

public interface IEqUnitDict {
	ReObject findAll();
	ReObject del(EqUnitDict obj);
	ReObject save(EqUnitDict obj);
}
