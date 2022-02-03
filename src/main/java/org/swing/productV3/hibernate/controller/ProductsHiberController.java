package org.swing.productV3.hibernate.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.swing.productV3.hibernate.util.HibernateUtil;
import org.swing.productV3.hibernate.entity.Company;
import org.swing.productV3.hibernate.entity.Product;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductsHiberController extends HibernateUtil {
    SessionFactory sessionFactory = getSessionFactory();

    /*
     * Get all Companies from DB
     */
    public List<Company> getAllCompanies() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Company.class);
        Root<Company> root = cq.from(Company.class);
        cq.select(root);

        Query query = session.createQuery(cq);
        List<Company> companyList = query.getResultList();
        session.close();
        return companyList;
    }

    /*
     * Get all Products from DB by id Company
     */
    public List<Product> getProductsByCompany(Company c) {
        return c.getProductsList();
    }

    /*
     * Add Product at the DB
     */
    public void add(Product p) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(p);
        session.close();
    }

    /*
     * Add Company at the DB
     */
    public void add(Company c) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(c);
        session.close();
    }

    /*
     * Delete Product from DB by id
     */
    public void deleteProduct(Product p) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(p);
        session.close();
    }

    /*
     * Delete Company from DB
     */
    public void deleteCompany(Company c) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(c);
        session.close();
    }

    /*
     * Update Product at the DB
     */
    public void update(Product p) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(p);
        session.close();
    }
}
