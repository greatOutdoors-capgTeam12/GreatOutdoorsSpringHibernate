package com.capgemini.go.bean;

//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;


/*
 * Remember, the attribute names in the Bean Class must match those that are sent from remote server in the json object
 * The below is the form used in the angular component
 * deliveryTimeReportForm = this.fb.group({
    retailerId: [''],
    reportType: [''],
    startDate: [''],
    endDate: ['']
  });
 */
//@XmlRootElement
public class ReportFormBean {
    public String retailerId;
    public String reportType;
    public String startDate;
    public String endDate;
    public Double bonus;
    public Double target;
}

