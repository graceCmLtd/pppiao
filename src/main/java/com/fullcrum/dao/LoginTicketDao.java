package com.fullcrum.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fullcrum.model.sys.LoginTicketEntity;

@Mapper
public interface LoginTicketDao {
	
	String TABLE_NAEM = " ppp_login_ticket ";
    String INSERT_FIELDS = " user_id, ticket, expired, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",INSERT_FIELDS,") values (#{userId},#{ticket},#{expired},#{status})"})
    void insertLoginTicket(LoginTicketEntity loginTicketEntity);
    
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    LoginTicketEntity seletById(int id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where ticket=#{ticket}"})
    LoginTicketEntity seletByTicket(String ticket);

    @Update({"update",TABLE_NAEM,"set status = #{status} where ticket = #{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    void deleteById(int id);

}
