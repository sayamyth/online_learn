package com.example.online_learn.service.imp;

import com.example.online_learn.dao.TypeDao;
import com.example.online_learn.entity.Type;
import com.example.online_learn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeImp implements TypeService {
    @Autowired
    private TypeDao typeDao;


    @Override
    public Type getTypeByName(String typename) {
        return typeDao.findByTypeName(typename);
    }

    @Override
    public List<Type> getType() {
        return typeDao.findAll();
    }

    @Override
    public Page<Type> getTypeWithPage(PageRequest pageRequest) {
        return typeDao.findAll(pageRequest);
    }

    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Override
    public int deleteTypeById(String id) {
        try {

            typeDao.deleteById(Long.valueOf(id).longValue());
            return 1;
        }catch (Exception e){
            return 0;
        }

    }
}
