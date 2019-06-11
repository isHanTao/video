package com.exam.controller.admin;

import com.exam.annotation.Security;
import com.exam.bean.Msg;
import com.exam.enums.RoleEnum;
import com.exam.service.admin.LabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Project: video
 * @Package: com.exam.controller.admin
 * @Author: 罗致远
 * @Date: 2018-10-11 14:33
 * @Description:
 * @Param:
 **/

@RestController
@RequestMapping("/admin")
public class LabelController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    private LabelService labelService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/queryLabel", method = RequestMethod.POST)
    public Msg queryLabel(@RequestBody Map<String, String> classifyMap) throws Exception {
        String classify = classifyMap.get("classify");
        String pageNum = classifyMap.get("pageNum");
        return labelService.queryLabel(classify, pageNum);
    }

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/queryLabelDetail", method = RequestMethod.POST)
    public Msg queryLabelDetail(@RequestBody Map<String, String> labelIdMap) throws Exception {
        String labelId = labelIdMap.get("labelId");
        return labelService.queryLabelDetail(labelId);
    }

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/modifyLabel", method = RequestMethod.PUT)
    public Msg modifyLabel(@RequestBody Map<String, String> labelMap) throws Exception {
        String labelId = labelMap.get("labelId");
        String classify = labelMap.get("newClassify");
        String newLabel = labelMap.get("newLabel");
        return labelService.modifyLabel(labelId, classify, newLabel);
    }

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/deleteLabel", method = RequestMethod.DELETE)
    public Msg deleteLabel(@RequestBody Map<String, String> labelMap) throws Exception {
        String labelId = labelMap.get("labelId");
        return labelService.deleteLabel(labelId);
    }

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/addLabel", method = RequestMethod.POST)
    public Msg addLabel(@RequestBody Map<String, String> labelMap) throws Exception {
        String classify = labelMap.get("classify");
        String label = labelMap.get("label");
        return labelService.addLabel(label, classify);
    }
}
