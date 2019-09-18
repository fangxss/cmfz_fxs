package com.fxs.impl;

import com.fxs.dao.ChapterDao;
import com.fxs.entity.Chapter;
import com.fxs.service.ChapterService;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterDao chapterDao;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public HashMap<String, Object> selectAll(Integer page, Integer rows,String albumId) {
        Integer pages=(page-1)*rows;
        List<Chapter> chapters = chapterDao.selectBypage(pages, rows,albumId);
        Integer count = chapterDao.selectCount(albumId);
        Integer total=count%rows==0 ? count /rows :count/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("records",count);
        map.put("total",total);
        map.put("page",page);
        map.put("rows",chapters);
        return map;
    }

    @Override
    public String addChapter(Chapter chapter) {
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapter.setUp_date(new Date());
        chapterDao.insertChapter(chapter);
        return s;
    }

    @Override
    public HashMap<String, Object> updateChapter(Chapter chapter) {
        return null;
    }

    @Override
    public void deleteChapter(String id) {

    }

    @Override
    public void uploadChapter(MultipartFile url, String id, HttpSession session)throws Exception {
        String realPath = session.getServletContext().getRealPath("/upload/music");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = url.getOriginalFilename();
        String name=new Date().getTime()+"_"+filename;
        System.out.println(filename);
        File file1 = new File(realPath, name);
        url.transferTo(file1);
            //获取文件大小
            long size = url.getSize();
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String s = String.valueOf(size);
            Double aDouble = Double.valueOf(s);
            Double d=aDouble/1024/1024;
            String sizes = decimalFormat.format(d)+"MB";

            //获取文件时长
            AudioFileIO fileIO = new AudioFileIO();
        File file2 = new File(realPath, name);
        AudioFile audio = fileIO.readFile(file2);
            AudioHeader audioHeader = audio.getAudioHeader();
            int length = audioHeader.getTrackLength();
            String duration=length/60+"分"+length%60+"秒";

            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setUrl(name);
            chapter.setSize(sizes);
            chapter.setDuration(duration);
            chapterDao.updateChapter(chapter);


    }
}
