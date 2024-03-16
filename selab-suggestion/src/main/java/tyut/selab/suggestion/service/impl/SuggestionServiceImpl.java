package tyut.selab.suggestion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.suggestion.domain.AESEncryptionExample;
import tyut.selab.suggestion.domain.PageParam;
import tyut.selab.suggestion.domain.entity.SuggestionEntity;
import tyut.selab.suggestion.mapper.SuggestionMapper;
import tyut.selab.suggestion.service.ISuggestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SuggestionServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:35
 * @Version: 1.0
 **/
@Service
public class SuggestionServiceImpl extends ServiceImpl<SuggestionMapper, SuggestionEntity> implements ISuggestionService {
    @Autowired
    private SuggestionMapper suggestionMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public AjaxResult getallSuggestion(PageParam pageParam){
        String suggestionKey = redisCache.getCacheObject("suggestion_key");
        if (StringUtils.isNull(suggestionKey)) {
            return AjaxResult.error("请输入密钥!");
        }
        List<SuggestionEntity> suggestionList = suggestionMapper.selectAllSuggestion();
        suggestionList.remove(0);
        List<SuggestionEntity> suggestionList1 = new ArrayList<SuggestionEntity>();
        for (SuggestionEntity suggestion : suggestionList){
            System.out.println(suggestion);
            try {
                suggestion.setSuggestionContent(AESEncryptionExample.decrypt(suggestion.getSuggestionContent(),suggestionKey));
                suggestion.setSuggestionUser(AESEncryptionExample.decrypt(suggestion.getSuggestionUser(),suggestionKey));
                suggestionList1.add(suggestion);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());

        // 使用PageInfo对查询结果进行包装，获取分页相关信息
        PageInfo<SuggestionEntity> suggestionIPage = new PageInfo<>(suggestionList1);
        // 将查询结果的List赋值给Page对象的构造函数
        return AjaxResult.success(suggestionIPage);
    }
    @Override
    public AjaxResult addSuggestion(SuggestionEntity suggestion) {
        suggestion.setSuggestionUser(SecurityUtils.getLoginUser().getUser().getNickName());
        SuggestionEntity suggestionEntity =suggestionMapper.selectSuggestionById(1);
        String key = suggestionEntity.getSuggestionContent();
        String suggestionContent =suggestion.getSuggestionContent();
        String suggestionUser = suggestion.getSuggestionUser();
        try {
            suggestion.setSuggestionContent(AESEncryptionExample.encrypt(suggestionContent,key));
            suggestion.setSuggestionUser(AESEncryptionExample.encrypt(suggestionUser,key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        suggestionMapper.insertSuggestion(suggestion);
        return AjaxResult.success("添加成功！");
    }
    @Override
    public AjaxResult verifySuggestionKey(String suggestionKey){
        System.out.println(suggestionKey);
        SuggestionEntity suggestionEntity =suggestionMapper.selectSuggestionById(1);
        if(suggestionEntity.getSuggestionContent().equals(AESEncryptionExample.Encrypt(suggestionKey))){
            redisCache.setCacheObject("suggestion_key",AESEncryptionExample.Encrypt(suggestionKey),5, TimeUnit.MINUTES);
            return AjaxResult.success("验证成功！");
        }else {
            return AjaxResult.error();
        }
    }
    @Override
    public AjaxResult reviseSuggestionKey(String suggestionKey){
        String suggestionKey1 = redisCache.getCacheObject("suggestion_key");
        if (StringUtils.isNull(suggestionKey1)) {
            return AjaxResult.error("请输入密钥!");
        }
        suggestionMapper.updateSuggestionKey(1,AESEncryptionExample.Encrypt(suggestionKey));
        return AjaxResult.success("修改成功！");
    }

    @Override
    public AjaxResult getSuggestionById(Integer suggestionId){
        String suggestionKey = redisCache.getCacheObject("suggestion_key");
        if (StringUtils.isNull(suggestionKey)||suggestionId==1) {
            return AjaxResult.error("请输入密钥!");
        }
        SuggestionEntity suggestion =suggestionMapper.selectSuggestionById(suggestionId);
        try {
            suggestion.setSuggestionContent(AESEncryptionExample.decrypt(suggestion.getSuggestionContent(),suggestionKey));
            suggestion.setSuggestionUser(AESEncryptionExample.decrypt(suggestion.getSuggestionUser(),suggestionKey));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success(suggestion);
    }

    @Override
    public AjaxResult deleteSuggestionById(Integer suggestionId){
        String suggestionKey = redisCache.getCacheObject("suggestion_key");
        if (StringUtils.isNull(suggestionKey)||suggestionId==1) {
            return AjaxResult.error("请输入密钥!");
        }
        if (suggestionMapper.deleteSuggestionById(suggestionId)){
            return AjaxResult.success("删除成功！");
        }else {
            return AjaxResult.error();
        }
    }
}
