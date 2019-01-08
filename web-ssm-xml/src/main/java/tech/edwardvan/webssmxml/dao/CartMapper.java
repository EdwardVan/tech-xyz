package tech.edwardvan.webssmxml.dao;

import tech.edwardvan.webssmxml.model.Cart;

import java.util.List;

/**
 * 购物车dao类
 *
 * @author EdwardVan
 */
public interface CartMapper {

    int insert(Cart cart);

    Integer deleteByPrimaryKey(Integer id);

    Long updateByPrimaryKey(Cart cart);

    Cart selectByPrimaryKey(Integer id);

    List<Cart> selectByUserId(Integer userId);

}