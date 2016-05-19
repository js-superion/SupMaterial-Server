package cn.superion.cssd.organization.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.deptPerson.entity.CdPersonDict;
import cn.superion.cssd.entity.CssdPersonTrain;

public interface ICssdConfig {
    ReObject findPersonByCodeAndName(ParameterObject fparameter);
    ReObject findTrainListByPersonId(String personId);
    ReObject checkPerson(String personId,String fstrCurrentState);
    ReObject savePersonAndTrains(CdPersonDict fperson,List<CssdPersonTrain> flstTrains);
    ReObject delPerson(String fstrPersonId);
    ReObject findTrainFileByTrainAutoId(String fstrTrainAutoId);
}
