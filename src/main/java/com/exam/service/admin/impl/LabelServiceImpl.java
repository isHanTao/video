package com.exam.service.admin.impl;

import com.exam.bean.Msg;
import com.exam.bean.admin.LabelBean;
import com.exam.config.ClassifyStatic;
import com.exam.config.RegexStatic;
import com.exam.dao.admin.LabelDao;
import com.exam.enums.ResultEnum;
import com.exam.service.admin.AddLogInfoService;
import com.exam.service.admin.LabelService;
import com.exam.util.type.StringUtil;
import com.exam.util.type.TypeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 罗致远
 * @Project: video
 * @Package:com.exam.service.admin.impl
 * @date 2018/10/11 18:39
 * @description
 **/

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private AddLogInfoService addLogInfoService;
    private Logger log = LoggerFactory.getLogger(getClass());

    private static final Integer maxLabelIdSize = 11;
    private static final Integer minLabelIdSize = 5;
    private static final Integer maxLabelSize = 20;
    private static final String  REGEX_NUMBER = "[0-2]";
    private static final String  checkInput ="%";
    private static final String staticLabelName = "其他";

    @Override
    public Msg queryLabel(String classify, String pageNum) throws Exception {
        Integer pageSize = TypeUtil.createInt(3);
        if (StringUtil.isEmpty(classify)){
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "分类信息不能为空！");
        }
        if(! checkInput.equals(classify) && !Pattern.compile(REGEX_NUMBER).matcher(classify).matches()){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "参数不合法！");
        }
        int newPageNum = 1;
        try {
            newPageNum = Integer.parseInt(pageNum);
        }catch (NumberFormatException e){
           pageSize = 9999999;
        }
        PageHelper.startPage(newPageNum, pageSize);
        List<LabelBean> labelList = labelDao.queryLabel(classify);
        PageInfo pageInfo = new PageInfo(labelList, 5);
        return Msg.success("查询成功！", pageInfo);
    }

    @Override
    public Msg queryLabelDetail(String labelId) throws Exception {
        if (StringUtil.isEmpty(labelId)){
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "标签不能为空！");
        }
        if (!checkLabelId(labelId)){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "标签id格式错误！");
        }
        int newLabelId;
        try{
            newLabelId = Integer.parseInt(labelId);
        }catch (NumberFormatException e){
            log.warn("缺少标签ID信息");
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "标签id格式错误！");
        }
        LabelBean labelBean = labelDao.queryLabelDetail(newLabelId);
        return Msg.success("查询成功！", labelBean);
    }

    @Override
    public Integer queryLabelId(String label, Integer classify) throws Exception {
        return labelDao.queryLabelId(label, classify);
    }

    @Override
    //@CacheEvict(value = "videos",allEntries = true)
    public Msg addLabel(String label, String classify) throws Exception {
        if (StringUtil.isEmpty(classify) || StringUtil.isEmpty(label)){
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "新增标签的分类和标签名不能为空！");
        }
        int newClassify = -1;
        try {
            newClassify = Integer.parseInt(classify);
        }catch (NumberFormatException e){
            log.warn("分页信息错误");
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "分类信息有误！");
        }
        if (newClassify < ClassifyStatic.FOOTBALL || newClassify > ClassifyStatic.SPORTS){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "分类信息有误！");
        }
        if (label.length() > maxLabelSize){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "标签名格式有误！");
        }
        Integer hasLabel =  labelDao.queryLabelId(label,newClassify);
        if(!StringUtils.isEmpty(hasLabel)){
            return Msg.fail(ResultEnum.FAIL, "该分类下标签已存在");
        }
        labelDao.addLabel(label, newClassify);
        Integer newLabelId = labelDao.queryLabelId(label, newClassify);
        addLogInfoService.insertLogInfo("新增", "L" + newLabelId, label, 1);
        return Msg.success("新增标签成功！");
    }

    @Override
    //@CacheEvict(value = "videos",allEntries = true)
    public Msg modifyLabel(String labelId, String classify, String newLabel) throws Exception {
        Integer newLabelId = null;
        Integer newClassify = -1;
        if (StringUtil.isEmpty(labelId) || StringUtil.isEmpty(newLabel) || StringUtil.isEmpty(classify)){
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "标签id,新修改的标签名,分类不能为空！");
        }
        if (!checkLabelId(labelId)){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "标签id格式错误！");
        }
        try {
           newLabelId = Integer.parseInt(labelId);
           newClassify = Integer.parseInt(classify);
        }catch (NumberFormatException e){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "格式错误！");
        }
        LabelBean labelBean = labelDao.queryLabelDetail(newLabelId);
        String oldLabel = labelBean.getLabel();
        if (newClassify < ClassifyStatic.FOOTBALL || newClassify > ClassifyStatic.SPORTS){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "分类信息有误！");
        }
        if (newLabel.length() > maxLabelSize){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "标签名格式有误！");
        }
        labelDao.modifyLabel(newLabelId, newClassify, newLabel);
        addLogInfoService.insertLogInfo("修改", "L" + labelId, oldLabel, 1);
        return Msg.success("修改成功！");
    }

    @Override
    //@CacheEvict(value = "videos",allEntries = true)
    public Msg deleteLabel(String labelId) throws Exception {
        Integer newLabelId = null;
        Integer modifyLabelId = null;
        if (StringUtil.isEmpty(labelId)){
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "标签id不能为空！");
        }
        if (!checkLabelId(labelId)){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "标签id格式错误！");
        }try {
            newLabelId = Integer.parseInt(labelId);
        }catch (NumberFormatException e){
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "格式错误！");
        }
        LabelBean labelBean = labelDao.queryLabelDetail(newLabelId);
        String oldLabel = labelBean.getLabel();
        if (staticLabelName.equals(oldLabel)){
            return Msg.success("'其他'标签不能被删除！");
        }
        Integer classify = labelBean.getClassify();
        modifyLabelId = labelDao.queryLabelId(staticLabelName, classify);
        if (StringUtils.isEmpty(modifyLabelId)){
            labelDao.addLabel(staticLabelName, classify);
            Integer newInsertLabelId = labelDao.queryLabelId(staticLabelName, classify);
            addLogInfoService.insertLogInfo("新增", "L" + newInsertLabelId, staticLabelName, 1);
            modifyLabelId = labelDao.queryLabelId(staticLabelName, classify);
        }
        labelDao.modifyVideos(modifyLabelId, newLabelId);
        labelDao.deleteLabel(newLabelId);
        addLogInfoService.insertLogInfo("删除", "L" + labelId, oldLabel, 1);
        return Msg.success("删除成功！");
    }

    private boolean checkLabelId(String labelId) throws Exception {
        List<Integer> labelIdList = labelDao.queryLabelIdAll();
        if (!Pattern.compile(RegexStatic.NUMBER).matcher(labelId).matches() || labelId.length() > maxLabelIdSize || labelId.length() < minLabelIdSize){
            return false;
        }
        for (int i = 0; i < labelIdList.size(); i++){
            if (String.valueOf(labelIdList.get(i)).equals(labelId)){
                return true;
            }
        }
        return false;
    }
}
