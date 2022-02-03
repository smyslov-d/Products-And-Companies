package org.swing.productV3.jdbs.controller;

import org.swing.productV3.jdbs.SystemProblemException;
import org.swing.productV3.jdbs.entity.CompanyDAOEntity;
import org.swing.productV3.jdbs.entity.ProductsDAOEntity;
import org.swing.productV3.jdbs.util.JDBSConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * All relations with Database
 */
public class Controller extends JDBSConnectionUtil {

        /*
         * Get all Companies from DB
         */
        public List<CompanyDAOEntity> getAllCompanies() {
            List<CompanyDAOEntity> companies = new ArrayList<>();
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM company;";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    CompanyDAOEntity c = new CompanyDAOEntity(
                            rs.getInt("id"),
                            rs.getString("name"));
                    companies.add(c);
                }
                rs.close();
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
            return companies;
        }

        /*
         * Get all Products from DB by id Company
         */
        public List<ProductsDAOEntity> getProductsByCompany(CompanyDAOEntity c) {
            List<ProductsDAOEntity> products = new ArrayList<>();
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM goods WHERE idComp ='" + c.id + "';";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    ProductsDAOEntity g = new ProductsDAOEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("idComp"),
                            rs.getInt("price"));
                    products.add(g);
                }
                rs.close();
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
            return products;
        }

        /*
         * Add Product at the DB
         */
        public void add(ProductsDAOEntity p) {
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO goods(idComp, name, price) VALUES (" + "'" + p.idComp + "', '" + p.name + "', '" + p.price + "');";
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

        /*
         * Add Company at the DB
         */
        public void add(CompanyDAOEntity c) {
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO company(name) VALUES('" + c.name + "')";
                statement.executeUpdate(sql);
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

        /*
         * Delete Product from DB by id
         */
        public void deleteProduct(ProductsDAOEntity p) {
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "DELETE FROM goods WHERE id = '" + p.id + "';";
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

        /*
         * Delete Company from DB by id
         */
        public void deleteCompany(CompanyDAOEntity c) {
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "DELETE FROM company WHERE id = '" + c.id + "';";
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

        /*
         * Update Product at the DB by id
         */
        public void update(ProductsDAOEntity p) {
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "UPDATE goods SET name = '" + p.name + "', idComp = '" + p.idComp + "', price = '" + p.price + "' WHERE id = '" + p.id + "';";
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

        /*
         * Update Company at the DB by id
         */
        public void update(CompanyDAOEntity c) {
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();
                String sql = "UPDATE company SET name = '" + c.name + "' WHERE id = '" + c.id + "';";
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                throw new SystemProblemException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

        /*
         * Call necessary method: add() or update()
         */
        public void persist(CompanyDAOEntity c) {
            if (c.id == 0) {
                add(c);
            } else {
                update(c);
            }
        }

        /*
         * Call necessary method: add() or update()
         */
        public void persist(ProductsDAOEntity p) {
            if (p.id == 0) {
                add(p);
            } else {
                update(p);
            }
        }
    }
