package org.swing.productV3.jdbs;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.swing.productV3.jdbs.controller.Controller;
import org.swing.productV3.jdbs.entity.CompanyDAOEntity;
import org.swing.productV3.jdbs.entity.ProductsDAOEntity;

import java.util.List;

/*
 * Test application
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class TestCase {
    public static Controller controller = new Controller();
    //Count Companies at DB
    public static int countComp = 7;

    /*
     * Get all companies from DB
     */
    @Test
    public void num1_GetAllCompaniesTest() {
        List<CompanyDAOEntity> compList;
        compList = controller.getAllCompanies();
        Assert.assertEquals(compList.size(), countComp);
    }

    /*
     * Add Company at the DB
     */
    @Test
    public void num2_AddCompany() {
        List<CompanyDAOEntity> beforeCompList = controller.getAllCompanies();
        CompanyDAOEntity testCompany = new CompanyDAOEntity("Test_Name_Company");
        controller.add(testCompany);
        List<CompanyDAOEntity> afterCompList = controller.getAllCompanies();
        Assert.assertEquals(beforeCompList.size() + 1, afterCompList.size());
    }

    /*
     * Get all Products from DB by id Company
     */
    @Test
    public void num3_getGoodsByCompanyTest() {
        List<CompanyDAOEntity> compList = controller.getAllCompanies();
        List<ProductsDAOEntity> productsList = controller.getProductsByCompany(compList.get(countComp));
        Assert.assertEquals(productsList.size(), 0);
    }

    /*
     * Add Product at the DB
     */
    @Test
    public void num4_addProduct() {
        //Get all Companies
        List<CompanyDAOEntity> compList = controller.getAllCompanies();
        //Get instance "Test_Company"
        CompanyDAOEntity testCompany = compList.get(countComp);
        //Get all products before test method add()
        List<ProductsDAOEntity> beforeProductsList = controller.getProductsByCompany(testCompany);
        //Create Test_Product
        ProductsDAOEntity testProduct = new ProductsDAOEntity("Test_Name_Product", testCompany.id, 0);
        controller.add(testProduct);
        //Get all products after test method add()
        List<ProductsDAOEntity> afterProductsList = controller.getProductsByCompany(testCompany);
        Assert.assertEquals(beforeProductsList.size() + 1, afterProductsList.size());
    }

    /*
     * Update Company
     */
    @Test
    public void num5_updateCompany() {
        //Get all Companies
        List<CompanyDAOEntity> compList = controller.getAllCompanies();
        //Get instance "Test_Company"
        CompanyDAOEntity beforeTestCompany = compList.get(countComp);
        //Create new name
        String testNewName = "Test_New_Name";
        //Assign new name
        beforeTestCompany.name = testNewName;
        //Update Company
        controller.update(beforeTestCompany);
        //Get all Companies
        compList = controller.getAllCompanies();
        //Get instance new "Test_Company"
        CompanyDAOEntity afterTestCompany = compList.get(countComp);
        Assert.assertEquals(testNewName, afterTestCompany.name);
    }

    /*
     * Update Product
     */
    @Test
    public void num6_updateProduct() {
        //Get all Companies
        List<CompanyDAOEntity> compList = controller.getAllCompanies();
        //Get instance "Test_Company"
        CompanyDAOEntity testCompany = compList.get(countComp);
        //Get all products before test method update()
        List<ProductsDAOEntity> productsList = controller.getProductsByCompany(testCompany);
        //Get instance "Test_Product"
        ProductsDAOEntity beforeTestProduct = productsList.get(0);
        //Create new name
        String testNewName = "Test_New_Name";
        //Assign new name
        beforeTestProduct.name = testNewName;
        //Update Product
        controller.update(beforeTestProduct);
        //Get all products after test method update()
        productsList = controller.getProductsByCompany(testCompany);
        //Get instance new "Test_Product"
        ProductsDAOEntity afterTestProduct = productsList.get(0);
        Assert.assertEquals(testNewName, afterTestProduct.name);
    }

    /*
     * Delete Product from DB
     */
    @Test
    public void num7_deleteProduct() {
        //Get all Companies
        List<CompanyDAOEntity> compList = controller.getAllCompanies();
        //Get instance "Test_Company"
        CompanyDAOEntity testCompany = compList.get(countComp);
        //Get all Products by id "Test_Company"
        List<ProductsDAOEntity> beforeProductsList = controller.getProductsByCompany(testCompany);
        //Get instance "Test_Product"
        ProductsDAOEntity testProduct = beforeProductsList.get(0);
        controller.deleteProduct(testProduct);
        //After get all Products
        List<ProductsDAOEntity> afterProductsList = controller.getProductsByCompany(testCompany);
        Assert.assertEquals(beforeProductsList.size() - 1, afterProductsList.size());
    }

    /*
     * Delete Company from DB
     */
    @Test
    public void num8_deleteCompany() {
        List<CompanyDAOEntity> beforeCompList = controller.getAllCompanies();
        controller.deleteCompany(beforeCompList.get(countComp));
        List<CompanyDAOEntity> afterCompList = controller.getAllCompanies();
        Assert.assertEquals(beforeCompList.size() - 1, afterCompList.size());
    }
}