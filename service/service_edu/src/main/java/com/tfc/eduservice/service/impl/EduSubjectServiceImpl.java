package com.tfc.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tfc.eduservice.entity.EduSubject;
import com.tfc.eduservice.entity.excel.SubjectData;

import com.tfc.eduservice.entity.subject.OneSubject;
import com.tfc.eduservice.entity.subject.TwoSubject;
import com.tfc.eduservice.listener.SubjectExcelListener;
import com.tfc.eduservice.mapper.EduSubjectMapper;
import com.tfc.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //保存上传文件
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public List<OneSubject> getAllOneSubject() {
        //查询一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //查询二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> TwoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list分类集合，用于封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();


        //封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);


            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //封装二级分类
            for (int m = 0; m < TwoSubjectList.size(); m++) {
                EduSubject twoEduSubject = TwoSubjectList.get(m);
                if (twoEduSubject.getParentId().equals(eduSubject.getId())){
                        TwoSubject twoSubject = new TwoSubject();
                        BeanUtils.copyProperties(twoEduSubject,twoSubject);
                        twoFinalSubjectList.add(twoSubject);
                }

            }
            //全部放进第一个集合里面
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }
}
