package org.zerock.shoppingcart.repository;

import java.io.IOException;
import java.util.Date;

import java.util.List;
 
import javax.persistence.NoResultException;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.zerock.shoppingcart.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 

@Transactional
@Repository
public class ProductRepository {

	@Autowired
    private SessionFactory sessionFactory;
	
	public Product findProduct(String code) {
        try {
            String sql = "Select e from " + Product.class.getName() + " e Where e.code =:code ";
 
            Session session = this.sessionFactory.getCurrentSession();
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("code", code);
            return (Product) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
	
	public List<Product> findAll() {
		try {
			String sql = "Select e from " + Product.class.getName() + " e ";
			
			Session session = this.sessionFactory.getCurrentSession();
			Query<Product> query = session.createQuery(sql, Product.class);
			
			List<Product> results = query.list();
	        return results;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(String code) {
    	try {
    		String sql = "delete from Product where code = :code1 ";
    		
    		Session session = this.sessionFactory.getCurrentSession();
    		Query query = session.createQuery(sql);
    		query.setParameter("code1",code);
    		
    		int rowsAffected = query.executeUpdate();
    		if (rowsAffected > 0) {
    		    System.out.println("Deleted " + rowsAffected + " rows.");
    		}

    	} catch (NoResultException e) {
        }
    }
	
	public List<Product> queryProducts(String likeName) {
        String sql = "Select new " + Product.class.getName() 
                + "(p.code, p.name, p.price) " + " from "
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        
        Session session = this.sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery(sql, Product.class);
        
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        
        List<Product> results = query.list();
        return results;
    }
}
