package com.wirecard.ezecom.dto;

import java.util.Date;

public class EBatchLogDto {
    private String sno;

    private int noSuccess;

    private int noFailure;

    private Date updateDate;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }


    public int getNoSuccess() {
		return noSuccess;
	}

	public void setNoSuccess(int noSuccess) {
		this.noSuccess = noSuccess;
	}

	public int getNoFailure() {
		return noFailure;
	}

	public void setNoFailure(int noFailure) {
		this.noFailure = noFailure;
	}

	public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}