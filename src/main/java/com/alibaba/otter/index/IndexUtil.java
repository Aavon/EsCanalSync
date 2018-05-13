package com.alibaba.otter.index;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;

public class IndexUtil {
	
	// 获取自定义类型字段定义
	@SuppressWarnings({ "unused", "rawtypes" })
	private static List<Field> getMappedFiled(Class clazz, List<Field> fields) {
        if (fields == null) {
            fields = new ArrayList<Field>();
        }

        Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(TableField.class)) {
                fields.add(field);
            }
        }
        if (clazz.getSuperclass() != null
                && !clazz.getSuperclass().equals(Object.class)) {
            getMappedFiled(clazz.getSuperclass(), fields);
        }

        return fields;
    }
	
	// 字段匹配
	public static void fieldMatch(Object index, List<Column> cols) {
		if (cols == null || cols.size() == 0 ) {
			return;
		}
		List<Field> fields = new ArrayList<Field>();
		// col --> field
		Map<String,Field> fieldMap = new HashMap<>();
		getMappedFiled(index.getClass(),fields);
		for( Field field : fields ) {
			if(field.isAnnotationPresent(TableField.class)) {
				TableField tf = field.getAnnotation(TableField.class);
				String colName = tf.name();
				field.setAccessible(true);
				fieldMap.put(colName, field);
			}
		}
		
		// set field value
		for( Column col : cols ) {
			String colName = col.getName();
			Field field = fieldMap.get(colName);
			try {
				if (field != null) {
					field.set(index, col.getValue());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
