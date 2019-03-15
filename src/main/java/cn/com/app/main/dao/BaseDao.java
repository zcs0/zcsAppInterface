package cn.com.app.main.dao;

import cn.com.app.assistcode.Page;
import cn.com.app.common.CommonStatic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2019/3/12.
 */
@Component
public class BaseDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private SessionFactory sessionFactory;

    public Session getSession(){
        try{
            return this.sessionFactory.getCurrentSession();
        }catch(Exception e){
            System.out.println(e.getStackTrace()+";;;BaseDao：getSession");
            return sessionFactory.openSession();
        }
    }

    /**
     * hibernate session save
     * @param obj
     * @return T
     * @exception
     * */
    public <T> T save(T obj) throws Exception{
        if(CommonStatic.ObjectIsEmpty(obj)){
            return null;
        }
        this.getSession().save(obj);
        return obj;
    }

    /**
     * hibernate hql 删除
     * this.deleteHQL("delete from TVLivingList t where t.id=:id and name=:name",param); param中需要有id属性和name并且有值
     * @param hql
     * @param  param
     * @return
     * @exception
     * */
    public boolean deleteHQL(String hql,Object param) throws Exception{
        if(CommonStatic.ObjectIsEmpty(hql)){
            return false;
        }
        if(param instanceof Map){
            Map<String,Object> par = (Map<String, Object>) param;
            this.getSession().createQuery(hql).setProperties(par).executeUpdate();
        }else{
            this.getSession().createQuery(hql).setProperties(param).executeUpdate();
        }
        return true;
    }

    /**
     * hibernate hql 删除
     * 查看Map中对应到要删除的实体的属性值，然后加入条件删除
     * @param map
     * @param  t
     * @return
     * @exception
     * */
    public <T> boolean deleteHQL(Map<String,Object> map,Class<T> t) throws Exception {
        if(!map.isEmpty()){
            StringBuilder sbu = new StringBuilder("delete from "+t.getSimpleName()+" ");
            Field[] fields = t.getDeclaredFields();
            boolean flag = true;
            for(Field f : fields){
                if(map.get(f.getName()) != null){
                    if(flag){
                        sbu.append(" where ");
                        flag = false;
                    }else{
                        sbu.append(" and ");
                    }
                    sbu.append(f.getName()+" =:"+f.getName());
                }
            }

            if(!flag){
                if(map.get("id") != null){ //手动添加id属性
                    sbu.append(" and id =:id ");
                }
                return this.deleteHQL(sbu.toString(),map);
            }else{
                if(map.get("id") != null){ //手动添加id属性
                    sbu.append(" where id =:id ");
                    return this.deleteHQL(sbu.toString(),map);
                }
            }
        }

        return false;
    }

    /**
     * JDBC sql 删除
     * this.deleteSQL("delete from tv_list where id=?",id);
     * @param sql
     * @param params
     * @return
     * @exception
     * */
    public boolean deleteSQL(String sql,Object... params) throws Exception{
        if(CommonStatic.ObjectIsEmpty(sql)){
            return false;
        }
        this.jdbcTemplate.update(sql,params);
        return true;
    }

    /**
     * hibernate hql 更新
     * @param hql
     * @param param
     * @return
     * @exception
     * */
    public boolean updateHQL(String hql,Object param) throws Exception{
        if(CommonStatic.ObjectIsEmpty(hql))
            return false;

        if(param instanceof Map){
            Map<String,Object> p = (Map<String, Object>) param;
            this.getSession().createQuery(hql).setProperties(p).executeUpdate();
        }else{
            this.getSession().createQuery(hql).setProperties(param).executeUpdate();
        }

        return true;
    }

    /**
     * hibernate hql 更新
     * 从map参数中提取实体对象的属性，通过id更新对应的属性值
     * @param map
     * @param t
     * @return
     * @exception
     * */
    public <T> boolean updateHQL(Map<String,Object> map,Class<T> t) throws Exception {
        if(!map.isEmpty() && map.get("id") != null && map.size()>1){//基于id更新，没有id则不执行，参数只有id也没有必要执行
            Field[] fields = t.getDeclaredFields();
            StringBuilder sbu = new StringBuilder("update "+t.getSimpleName()+" ");
            boolean flag = true;
            for(Field f : fields){
                if(!f.getName().equals("id") && map.get(f.getName()) != null){
                    if(flag){
                        sbu.append(" set ");
                        flag = false;
                    }else{
                        sbu.append(" , ");
                    }
                    sbu.append(f.getName()+" =:"+f.getName());
                }
            }
            sbu.append(" where id =:id ");
            if(!flag){
                return this.updateHQL(sbu.toString(),map);
            }
        }
        return false;
    }

    /**
     * hibernate session 更新对象
     * @param obj
     * @param <T>
     * @return
     * @exception
     * */
    public <T> T updateObject(T obj) throws Exception{
        if(CommonStatic.ObjectIsEmpty(obj))
            return null;
        this.getSession().update(obj);
        return obj;
    }

    /**
     * hibernate 批量保存
     * @param list
     * @param <T>
     * @return
     * @exception
     * */
    public <T>List<T> saveAll(List<T> list)throws Exception{
        for(int i=0;i<list.size();i++){
            if(i!=0&&i%20==0){
                this.getSession().flush();
            }
            this.save(list.get(i));
        }
        this.getSession().flush();
        return list;
    }

    /**
     * hibernate session hql查询 <br>  注意，toBean与changeMap都为空时，查询出映射类的列表 toBean与changeMap不能同时使用
     * @param hql
     * @param param
     * @param page
     * @param toBean
     * @param changeMap
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> baseFind(String hql,Object param,Page page,Class<T> toBean,Boolean changeMap)throws Exception{
        if(CommonStatic.ObjectIsEmpty(hql))
            return null;

        Query query = this.getSession().createQuery(hql);
        if(!CommonStatic.ObjectIsEmpty(toBean)){
            query.setResultTransformer(Transformers.aliasToBean(toBean));
        }
        if(!CommonStatic.ObjectIsEmpty(changeMap) && changeMap){
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        if(!CommonStatic.ObjectIsEmpty(param)){
            if(param instanceof Map){
                Map<String,Object> p = (Map<String, Object>) param;
                query.setProperties(p);
            }else{
                query.setProperties(param);
            }
        }
        if(!CommonStatic.ObjectIsEmpty(page)){
            query.setMaxResults(page.getLimit());
            query.setFirstResult(page.getStartRow());
        }
        return query.list();
    }

    /**
     * 直接hql无参数查询
     * @param hql
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T>List<T> find(String hql) throws Exception{
        return this.baseFind(hql,null,null,null,null);
    }

    /**
     * hibernate session hql查询   <br>  注意 这是直接查询出映射类的列表
     * @param hql
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> find(String hql ,Object param ) throws Exception{
        return this.baseFind(hql,param,null,null,null);
    }

    /**
     * hibernate session hql分页查询
     * @param hql
     * @param param
     * @param page
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> find(String hql ,Object param ,Page page) throws Exception{
        return this.baseFind(hql, param, page, null, null) ;
    }

    public <T> T get(Class<T> clss,Long id) throws Exception {
        return this.getSession().get(clss, id) ;
    }

    public<T> T update(T obj) throws Exception {
        return this.updateObject(obj);
    }

    /**
     * hibernate session 删除对象
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T delObject(T obj) throws Exception{
        this.getSession().delete(obj);
        return obj ;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
