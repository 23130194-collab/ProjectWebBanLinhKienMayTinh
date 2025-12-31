package com.example.demo1.service;

import com.example.demo1.dao.AttributeDao;
import com.example.demo1.dao.CategoryAttributeDao;
import com.example.demo1.model.Attribute;
import com.example.demo1.model.AttributePage;
import com.example.demo1.model.Category_Attribute;

import java.util.List;

public class AttributeService {
    private final AttributeDao attributeDao;
    private final CategoryAttributeDao categoryAttributeDao;

    public AttributeService() {
        this.attributeDao = new AttributeDao();
        this.categoryAttributeDao = new CategoryAttributeDao();
    }

    public void createAttributeForCategory(String name, String status, int categoryId, int displayOrder, int isFilterable) {
        Attribute attribute = new Attribute();
        attribute.setName(name);
        attribute.setStatus(status);
        int newAttributeId = attributeDao.addAttributeAndReturnId(attribute);

        Category_Attribute categoryAttribute = new Category_Attribute();
        categoryAttribute.setCategory_id(categoryId);
        categoryAttribute.setAttribute_id(newAttributeId);
        categoryAttribute.setDisplay_order(displayOrder);
        categoryAttribute.setIs_filterable(isFilterable);
        categoryAttributeDao.addCategoryAttribute(categoryAttribute);
    }

    public void updateAttributeAndCategoryLink(int attributeId, String name, String status, int categoryId, int displayOrder, int isFilterable) {
        // 1. Update the attribute itself
        Attribute attribute = new Attribute();
        attribute.setId(attributeId);
        attribute.setName(name);
        attribute.setStatus(status);
        attributeDao.updateAttribute(attribute);

        // 2. Update the link
        Category_Attribute categoryAttribute = new Category_Attribute();
        categoryAttribute.setAttribute_id(attributeId);
        categoryAttribute.setCategory_id(categoryId);
        categoryAttribute.setDisplay_order(displayOrder);
        categoryAttribute.setIs_filterable(isFilterable);
        categoryAttributeDao.updateCategoryAttribute(categoryAttribute);
    }

    public List<Attribute> getAttributes(String keyword, int limit, int offset) {
        return attributeDao.getAttributes(keyword, limit, offset);
    }

    public int countAttributes(String keyword) {
        return attributeDao.countAttributes(keyword);
    }

    public void deleteAttribute(int attributeId) {
        categoryAttributeDao.deleteByAttributeId(attributeId);
        attributeDao.deleteAttribute(attributeId);
    }

    public Attribute getAttributeById(int id) {
        return attributeDao.getAttributeById(id);
    }

    public AttributePage getPaginatedAttributes(String keyword, int limit, int offset) {
        List<Attribute> attributes = attributeDao.getAttributes(keyword, limit, offset);
        int totalAttributes = attributeDao.countAttributes(keyword);
        return new AttributePage(attributes, totalAttributes);
    }

    public boolean isAttributeExists(String name, int categoryId) {
        return attributeDao.isAttributeExists(name, categoryId);
    }
}
