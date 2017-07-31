package com.isesol.storm;

import java.util.Date;

/**
 * Created by Raphael on 2017/6/29.
 */
public class Topic2001 {
    String cfgEquSerialno;
    Double axFeedBackPos_A;
    Double axFeedBackPos_C;
    Double axFeedBackPos_X;
    Double axFeedBackPos_Y;
    Double axFeedBackPos_Z;
    Double axFeedBackVel_A;
    Double axFeedBackVel_C;
    Double axFeedBackVel_X;
    Double axFeedBackVel_Y;
    Double axFeedBackVel_Z;
    int cncrunlevel;
    Double feedScale;
    Double fReal;
    int modeStatus;
    int pl_parCount;
    int pl_timeOpe;
    Double posOnScreen_AC;
    Double posOnScreen_BC;
    Double posOnScreen_CC;
    Double posOnScreen_UC;
    Double posOnScreen_VC;
    Double posOnScreen_WC;
    Double posOnScreen_XC;
    Double posOnScreen_YC;
    Double posOnScreen_ZC;
    String selectedFile;
    /** 云工厂系统时间点 **/
    private Date sysTime;
    int spdLoad;
    int spdMtLoad;
    double spdSr;
    double spindleScale;
    String stdAxExist_A;
    String stdAxExist_B;
    String stdAxExist_C;
    String stdAxExist_U;
    String stdAxExist_V;
    String stdAxExist_W;
    String stdAxExist_X;
    String stdAxExist_Y;
    String stdAxExist_Z;
    String T; //当前刀具号

    public String getCfgEquSerialno() {
		return cfgEquSerialno;
	}

	public void setCfgEquSerialno(String cfgEquSerialno) {
		this.cfgEquSerialno = cfgEquSerialno;
	}

	public Double getAxFeedBackPos_A() {
		return axFeedBackPos_A;
	}

	public void setAxFeedBackPos_A(Double axFeedBackPos_A) {
		this.axFeedBackPos_A = axFeedBackPos_A;
	}

	public Double getAxFeedBackPos_C() {
		return axFeedBackPos_C;
	}

	public void setAxFeedBackPos_C(Double axFeedBackPos_C) {
		this.axFeedBackPos_C = axFeedBackPos_C;
	}

	public Double getAxFeedBackPos_X() {
		return axFeedBackPos_X;
	}

	public void setAxFeedBackPos_X(Double axFeedBackPos_X) {
		this.axFeedBackPos_X = axFeedBackPos_X;
	}

	public Double getAxFeedBackPos_Y() {
		return axFeedBackPos_Y;
	}

	public void setAxFeedBackPos_Y(Double axFeedBackPos_Y) {
		this.axFeedBackPos_Y = axFeedBackPos_Y;
	}

	public Double getAxFeedBackPos_Z() {
		return axFeedBackPos_Z;
	}

	public void setAxFeedBackPos_Z(Double axFeedBackPos_Z) {
		this.axFeedBackPos_Z = axFeedBackPos_Z;
	}

	public Double getAxFeedBackVel_A() {
		return axFeedBackVel_A;
	}

	public void setAxFeedBackVel_A(Double axFeedBackVel_A) {
		this.axFeedBackVel_A = axFeedBackVel_A;
	}

	public Double getAxFeedBackVel_C() {
		return axFeedBackVel_C;
	}

	public void setAxFeedBackVel_C(Double axFeedBackVel_C) {
		this.axFeedBackVel_C = axFeedBackVel_C;
	}

	public Double getAxFeedBackVel_X() {
		return axFeedBackVel_X;
	}

	public void setAxFeedBackVel_X(Double axFeedBackVel_X) {
		this.axFeedBackVel_X = axFeedBackVel_X;
	}

	public Double getAxFeedBackVel_Y() {
		return axFeedBackVel_Y;
	}

	public void setAxFeedBackVel_Y(Double axFeedBackVel_Y) {
		this.axFeedBackVel_Y = axFeedBackVel_Y;
	}

	public Double getAxFeedBackVel_Z() {
		return axFeedBackVel_Z;
	}

	public void setAxFeedBackVel_Z(Double axFeedBackVel_Z) {
		this.axFeedBackVel_Z = axFeedBackVel_Z;
	}

	public int getCncrunlevel() {
		return cncrunlevel;
	}

	public void setCncrunlevel(int cncrunlevel) {
		this.cncrunlevel = cncrunlevel;
	}

	public Double getFeedScale() {
		return feedScale;
	}

	public void setFeedScale(Double feedScale) {
		this.feedScale = feedScale;
	}

	public Double getfReal() {
		return fReal;
	}

	public void setfReal(Double fReal) {
		this.fReal = fReal;
	}

	public int getModeStatus() {
		return modeStatus;
	}

	public void setModeStatus(int modeStatus) {
		this.modeStatus = modeStatus;
	}

	public int getPl_parCount() {
		return pl_parCount;
	}

	public void setPl_parCount(int pl_parCount) {
		this.pl_parCount = pl_parCount;
	}

	public int getPl_timeOpe() {
		return pl_timeOpe;
	}

	public void setPl_timeOpe(int pl_timeOpe) {
		this.pl_timeOpe = pl_timeOpe;
	}

	public Double getPosOnScreen_AC() {
		return posOnScreen_AC;
	}

	public void setPosOnScreen_AC(Double posOnScreen_AC) {
		this.posOnScreen_AC = posOnScreen_AC;
	}

	public Double getPosOnScreen_BC() {
		return posOnScreen_BC;
	}

	public void setPosOnScreen_BC(Double posOnScreen_BC) {
		this.posOnScreen_BC = posOnScreen_BC;
	}

	public Double getPosOnScreen_CC() {
		return posOnScreen_CC;
	}

	public void setPosOnScreen_CC(Double posOnScreen_CC) {
		this.posOnScreen_CC = posOnScreen_CC;
	}

	public Double getPosOnScreen_UC() {
		return posOnScreen_UC;
	}

	public void setPosOnScreen_UC(Double posOnScreen_UC) {
		this.posOnScreen_UC = posOnScreen_UC;
	}

	public Double getPosOnScreen_VC() {
		return posOnScreen_VC;
	}

	public void setPosOnScreen_VC(Double posOnScreen_VC) {
		this.posOnScreen_VC = posOnScreen_VC;
	}

	public Double getPosOnScreen_WC() {
		return posOnScreen_WC;
	}

	public void setPosOnScreen_WC(Double posOnScreen_WC) {
		this.posOnScreen_WC = posOnScreen_WC;
	}

	public Double getPosOnScreen_XC() {
		return posOnScreen_XC;
	}

	public void setPosOnScreen_XC(Double posOnScreen_XC) {
		this.posOnScreen_XC = posOnScreen_XC;
	}

	public Double getPosOnScreen_YC() {
		return posOnScreen_YC;
	}

	public void setPosOnScreen_YC(Double posOnScreen_YC) {
		this.posOnScreen_YC = posOnScreen_YC;
	}

	public Double getPosOnScreen_ZC() {
		return posOnScreen_ZC;
	}

	public void setPosOnScreen_ZC(Double posOnScreen_ZC) {
		this.posOnScreen_ZC = posOnScreen_ZC;
	}

	public String getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public int getSpdLoad() {
		return spdLoad;
	}

	public void setSpdLoad(int spdLoad) {
		this.spdLoad = spdLoad;
	}

	public int getSpdMtLoad() {
		return spdMtLoad;
	}

	public void setSpdMtLoad(int spdMtLoad) {
		this.spdMtLoad = spdMtLoad;
	}

	public double getSpdSr() {
		return spdSr;
	}

	public void setSpdSr(double spdSr) {
		this.spdSr = spdSr;
	}

	public double getSpindleScale() {
		return spindleScale;
	}

	public void setSpindleScale(double spindleScale) {
		this.spindleScale = spindleScale;
	}

	public String getStdAxExist_A() {
		return stdAxExist_A;
	}

	public void setStdAxExist_A(String stdAxExist_A) {
		this.stdAxExist_A = stdAxExist_A;
	}

	public String getStdAxExist_B() {
		return stdAxExist_B;
	}

	public void setStdAxExist_B(String stdAxExist_B) {
		this.stdAxExist_B = stdAxExist_B;
	}

	public String getStdAxExist_C() {
		return stdAxExist_C;
	}

	public void setStdAxExist_C(String stdAxExist_C) {
		this.stdAxExist_C = stdAxExist_C;
	}

	public String getStdAxExist_U() {
		return stdAxExist_U;
	}

	public void setStdAxExist_U(String stdAxExist_U) {
		this.stdAxExist_U = stdAxExist_U;
	}

	public String getStdAxExist_V() {
		return stdAxExist_V;
	}

	public void setStdAxExist_V(String stdAxExist_V) {
		this.stdAxExist_V = stdAxExist_V;
	}

	public String getStdAxExist_W() {
		return stdAxExist_W;
	}

	public void setStdAxExist_W(String stdAxExist_W) {
		this.stdAxExist_W = stdAxExist_W;
	}

	public String getStdAxExist_X() {
		return stdAxExist_X;
	}

	public void setStdAxExist_X(String stdAxExist_X) {
		this.stdAxExist_X = stdAxExist_X;
	}

	public String getStdAxExist_Y() {
		return stdAxExist_Y;
	}

	public void setStdAxExist_Y(String stdAxExist_Y) {
		this.stdAxExist_Y = stdAxExist_Y;
	}

	public String getStdAxExist_Z() {
		return stdAxExist_Z;
	}

	public void setStdAxExist_Z(String stdAxExist_Z) {
		this.stdAxExist_Z = stdAxExist_Z;
	}

	public String getT() {
		return T;
	}

	public void setT(String t) {
		T = t;
	}

	private int sysMtime;

    public int getSysMtime() {
        return sysMtime;
    }

    public void setSysMtime(int sysMtime) {
        this.sysMtime = sysMtime;
    }
}
