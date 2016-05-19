package cn.superion.cssd.quality.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdPhysicsMaster;

public interface ICssdPhysics {
	ReObject findPhysicsByEquipNameInRange(ParameterObject fparam);
	
	ReObject savePhysicsMaster(CssdPhysicsMaster fphysicsMaster);
	
	ReObject  loadPic(String fstrPhyMasterAutoId);
	
	ReObject  delPhysicsMaster(String fstrAutoId);
	
	ReObject  checkPhysicsMaster(String fstrAutoId,String fstrCurrentState);
}
