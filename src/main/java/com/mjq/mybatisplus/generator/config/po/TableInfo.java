/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.mjq.mybatisplus.generator.config.po;

import com.mjq.mybatisplus.generator.config.StrategyConfig;
import com.mjq.mybatisplus.generator.config.rules.NamingStrategy;
import com.mjq.mybatisplus.generator.util.CollectionUtils;
import com.mjq.mybatisplus.generator.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <p>
 * 表信息，关联到当前字段信息
 * </p>
 *
 * @author YangHu
 * @since 2016/8/30
 */
public class TableInfo {

    private boolean convert;
    private String name;
    private String comment;

    private String entityName;
    private String objectName;
    private String mapperName;
    private String providerName;
    private String xmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private List<TableField> fields;
    private String keyFieldName;
    private String keyFieldType;
    private String primaryKeyName;
    private String keyCapitalFirstFieldName;
    /**
     * 公共字段
     */
    private List<TableField> commonFields;
    private Set<String> importPackages = new HashSet<>();
    private String fieldNames;

    public boolean isConvert() {
        return convert;
    }

    protected void setConvert(StrategyConfig strategyConfig) {
        if (strategyConfig.containsTablePrefix(name)) {
            // 包含前缀
            this.convert = true;
        } else if (strategyConfig.isCapitalModeNaming(name)) {
            // 包含
            this.convert = false;
        } else {
            // 转换字段
            if (NamingStrategy.underline_to_camel == strategyConfig.getColumnNaming()) {
                // 包含大写处理
                if (StringUtils.containsUpperCase(name)) {
                    this.convert = true;
                }
            } else if (!entityName.equalsIgnoreCase(name)) {
                this.convert = true;
            }
        }
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntityPath() {
        StringBuilder ep = new StringBuilder();
        ep.append(entityName.substring(0, 1).toLowerCase());
        ep.append(entityName.substring(1));
        return ep.toString();
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setEntityName(StrategyConfig strategyConfig, String entityName) {
        this.entityName = entityName;
        this.setConvert(strategyConfig);
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getKeyFieldName() {
        return keyFieldName;
    }

    public void setKeyFieldName(String keyFieldName) {
        this.keyFieldName = keyFieldName;
    }

    public String getKeyFieldType() {
        return keyFieldType;
    }

    public void setKeyFieldType(String keyFieldType) {
        this.keyFieldType = keyFieldType;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public String getKeyCapitalFirstFieldName() {
        return keyCapitalFirstFieldName;
    }

    public void setKeyCapitalFirstFieldName(String keyCapitalFirstFieldName) {
        this.keyCapitalFirstFieldName = keyCapitalFirstFieldName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        if (CollectionUtils.isNotEmpty(fields)) {
            this.fields = fields;
            // 收集导入包信息
            for (TableField field : fields) {
                if (null != field.getColumnType() && null != field.getColumnType().getPkg()) {
                    importPackages.add(field.getColumnType().getPkg());
                }
//                if (field.isKeyFlag()) {
//                    // 主键
//                    if (field.isConvert() || field.isKeyIdentityFlag()) {
//                        importPackages.add(com.baomidou.mybatisplus.annotation.TableId.class.getCanonicalName());
//                    }
//                    // 自增
//                    if (field.isKeyIdentityFlag()) {
//                        importPackages.add(com.baomidou.mybatisplus.annotation.IdType.class.getCanonicalName());
//                    }
//                } else if (field.isConvert()) {
//                    // 普通字段
//                    importPackages.add(com.baomidou.mybatisplus.annotation.TableField.class.getCanonicalName());
//                }
//                if (null != field.getFill()) {
//                    // 填充字段
//                    importPackages.add(com.baomidou.mybatisplus.annotation.TableField.class.getCanonicalName());
//                    importPackages.add(com.baomidou.mybatisplus.annotation.FieldFill.class.getCanonicalName());
//                }
            }
        }
    }

    public List<TableField> getCommonFields() {
        return commonFields;
    }

    public void setCommonFields(List<TableField> commonFields) {
        this.commonFields = commonFields;
    }

    public Set<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String pkg) {
        importPackages.add(pkg);
    }

    /**
     * 逻辑删除
     */
    public boolean isLogicDelete(String logicDeletePropertyName) {
        return fields.stream().anyMatch(tf -> tf.getName().equals(logicDeletePropertyName));
    }

    /**
     * 转换filed实体为xmlmapper中的basecolumn字符串信息
     *
     * @return
     */
    public String getFieldNames() {
        if (StringUtils.isEmpty(fieldNames)) {
            StringBuilder names = new StringBuilder();
            IntStream.range(0, fields.size()).forEach(i -> {
                TableField fd = fields.get(i);
                if (i == fields.size() - 1) {
                    names.append(fd.getName());
                } else {
                    names.append(fd.getName()).append(", ");
                }
            });
            fieldNames = names.toString();
        }
        return fieldNames;
    }

}
