package cn.edu.zju.ccnt.TFGW.DAO;

import java.util.ArrayList;

import cn.edu.zju.ccnt.TFGW.object.xmlInf.DiseaseInf;

public interface DiseaseDAO {
	public ArrayList<DiseaseInf> searchDistinctDisease(String[] params, int num, int count);
}
