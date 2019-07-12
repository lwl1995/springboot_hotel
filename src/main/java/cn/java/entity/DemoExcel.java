package cn.java.entity;
import org.springframework.format.annotation.DateTimeFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class DemoExcel {
 
 
    @Excel(name = "id" ,orderNum = "0")
    private Long seckill_id;
 

    @Excel(name = "姓名" ,orderNum = "1")
    private String name;
 

    @Excel(name = "数量" ,orderNum = "2")
    private int number;
 

    @Excel(name = "开始日期" ,orderNum = "3",importFormat = "yyyy-MM-dd HH:mm:ss")//exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date start_time;

    @Excel(name = "结束日期" ,orderNum = "4",importFormat = "yyyy-MM-dd HH:mm:ss")//exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date end_time;
 
  
    @Excel(name = "创建日期" ,orderNum = "5",importFormat = "yyyy-MM-dd HH:mm:ss")//exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;


	public Long getSeckill_id() {
		return seckill_id;
	}


	public void setSeckill_id(Long seckill_id) {
		this.seckill_id = seckill_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public Date getStart_time() {
		return start_time;
	}


	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}


	public Date getEnd_time() {
		return end_time;
	}


	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	@Override
	public String toString() {
		return "DemoExcel [seckill_id=" + seckill_id + ", name=" + name + ", number=" + number + ", start_time="
				+ start_time + ", end_time=" + end_time + ", create_time=" + create_time + "]";
	}
 
   
}