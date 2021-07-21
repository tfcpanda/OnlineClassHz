package com.tfc.eduservice.entity.section;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 田付成
 * @date 2021/7/22 1:12
 */

@Data
public class ChapterVo {


    private String id;

    private String title;

    List<VideoVo> videoList = new ArrayList<>();
}
