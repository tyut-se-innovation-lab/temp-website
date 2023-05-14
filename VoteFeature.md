# fa投票系统

## 功能需求

### 发起投票(/selab/vote/initiate)-白尚钰

1. 用户可以自定义自己投票的`投票结构（单个问题，多个问题）`和`投票组件（单选，多选，文本框）`
2. 在页面中应该拥有删除`投票组件`的功能
3. 当一名用户发起了一个投票时，所有人应当显示此投票为`新投票`，方便组织进行投票活动，**新投票的判断标准**，（先不管）

### 我的投票(/selab/vote/mine)-吕岩芳

1. 应该显示用户自己的历史投票内容，我发起的和我参与的
2. 投票应该明显的显示其**状态（进行中，结束，弃用）**
3. 应该显示结束时间
4. 最新发起的投票应该在列表最上方（前端）
5. 保证安全性
6. 权重需要计算并且返回（可写可不写）
7. 支持撤回操作 （发起人可撤回）
8. 提前结束功能 ,有一个接口

### 查看历史投票及结果(/selab/vote/history) -王俊峰

1. 投票应该明显的显示其状态（进行中，结束，弃用）
2. 应当全部匿名（不论前后端），数据传输和保存过程中不允许明文储存用户的信息
3. 应该显示结束时间
4. 最新发起的投票应该在列表最上方
5. 权重需要计算并且返回
5. 已参与人数
5. 多选投票选项百分比计算方法，假设有3个选项，分别为A,B,C,每个人的权重为a（变量，具体随人的身份不同而定），人数为n，选择A选项的人数为x，那么A选项的百分比为**∑a[i]/∑n[j]**，i的范围为[1,x],j的范围为[1,n]

### 参与投票（设计时保证代码开闭原则）(/selab/vote/join) -张喆斌

1. 每个不同的角色应该拥有不同的投票权重（只针对单选或多选的问题）
2. 投票的策略是管理员全局设置的
3. 当一个投票正在进行时，管理员修改全局策略，此投票应该按开始时的策略继续进行
4. 每次投票结束后，其结果不受后续的策略变动影响
5. 是否能参与投票的时间判断应该由服务器时间为主
5. 已参与和未参与，添加一个字段

### 管理投票策略  -白尚钰

1. 仅投票管理员可见
2. 可以切换投票的策略

### 加密解密 -张喆斌

​	1.使用MD5算法
​	前端直接加密JSON字符串

## 接口约束

- 发起投票:  

  * 访问参数

    * ```json
      {
      	"title": "",
        	"content": "",
      	
        	"deadline": "",
      	"voteQues": [
              {//单选，多选，文本框
                  "id": 0,
                  "type": "",	//类型，S表示单选，M表示多选，T表示文本框
                  "queContent": "", //文本框时传文本内容
                  "options": [
                      //单选，多选    文本框传一个空数组
                      {
                          "id": 0,
                          "content": "",  
                          "isSelect":"",
                          "type": "",
                          "other": ""
                      },
                  ]
              },
              
        ]
      }
      ```
  
  > 返回数据
  
  * ```json
    {
    	"code": 200,
  		"msg": "OK"
    }
    ```



- 历史投票


  - 返回历史投票信息粗略列表: /selab/vote/join/show {get}

```json
    [
        	{
        		id:"",	//投票id
        		title:"",//title,
        		state:0	//0,1,2状态
        		start:"",	//开始时间
              	isjoin:"",	//是否已经参与
        		end:"",	//结束时间
        		content:"",	//简介
        	}
    ]
```

  - 返回某次投票详细信息: /selab/vote/join/allInfo/:voteId {get}

```json
{
  "id": 0,  //问卷的id
  "title": "", //问卷的标题
  "content": "", //问卷简介
  "createdTime": "", //问卷创建时间
  "deadline": "",  //问卷截止时间
  "authority":"",  //问卷使用的权重策略
  "isWithdraw": "",  //是否被撤回
  "status":"",   //问卷当前状态
  "voteQues": [
    {
      "id": 0,     //问题的id
      "type": "",	//类型，S表示单选，M表示多选，T表示文本框
      "queContent": "", //单选多选的问题，文本的问题
      "options": [ //单选多选一个对象为一个选项，文本一个对象为用户填写的一条信息
        {
          "content": "", //单选多选：选项内容    文本：用户填写的信息
          "percentage":"" //百分比
          "type": "",  //选项种类  S表示单选，M表示多选，T表示文本框
          "other": "" 
        },	
      ]
    },
  ]
}
```




- 参与投票: /selab/vote/join {post}

  ```json
  {
    "id": 0,  //传入该投票的id即可
    "title": "", //问卷的标题
    "content": "", //问卷简介
    "createdTime": "", //问卷创建时间
    "deadline": "",  //问卷截止时间
    "authority":"",  //问卷使用的权重策略
    "isWithdraw": "",  //是否被撤回
    "status":"",   //问卷当前状态
    "voteQues": [
      {
        "id": 0,
        "type": "",	//类型，S表示单选，M表示多选，T表示文本框
        "queContent": "", //单选、多选、文本题目
        "options": [ //单选多选传选项，文本传用户输入的文本
          {
            "content": "", //选项内容   用户输入的文本内容
            "percentage":"" //百分比   上传投票时不用传这个
            "isSelect": "" //用户是否选中 上传投票时用户选中的选项传true，没选的传false   文本框为true
            "type": "", //选项的种类 S表示单选，M表示多选，T表示文本框
            "other": ""
          },	
        ]
      },
    ]
  }
  ```
  
  


  - 获取可参与的（未过期）投票粗略信息: /selab/vote/join/list {get}


```json
[
    {
    	id:"",	//投票id
    	title:"",//title,
    	state:0	,//0,1,2状态
    	start:"",	//开始时间
    	end:"",	//结束时间
    	content:"",	//简介
        isjoin:true	//是否已经参与
    }
 ]
```









  - 我的投票



  - 1 我发起的投票简略列表: /selab/vote/mine/launched/lists{get}
 ```json
    {
        msg=操作成功, 
        code=200, 
        data=[
        	Questionnaire{
                id=1,
                title='hhh', 
                content='hhh', 
                status='1', 
                creatTime=Tue Nov 15 09:52:46 CST 2022, 
                deadline=Mon Jan 30 16:12:07 CST 2023, 
                voteQues=null
            }
    	  ]
    }
 ```

  - 1 我参与的投票信息列表: /selab/vote/mine/joined/lists {get}

```
{
    msg=操作成功, 
    code=200, 
    data=[
    	Questionnaire{
            id=1,
            title='hhh', 
            content='hhh', 
            status='1', 
            creatTime=Tue Nov 15 09:52:46 CST 2022, 
            deadline=Mon Jan 30 16:12:07 CST 2023, 
            voteQues=null
        }
	]
}
```

- 1 对发起的投票进行撤回：/selab/vote/mine/delete/:voteId{delete}

```
{
    msg=撤回失败,
    code=500
}
{
    msg=撤回成功,
    code=200
}
```

- 1 提前结束功能：/selab/vote/mine/finish{PUT}

```
{
    msg=操作失败,
    code=500
}
{
    msg=操作成功,
    code=200
}
```

* 详细信息：/selab/vote/join/allInfo/:voteId {get}

```json
{
  "id": 0,
  "title": "",
  "content": "",
  "createdTime": "",
  "deadline": "",
  "authority":"",
  "status":"",
  "isWithdraw": "",
  "voteQues": [
    { //单选，多选
      "id": 0,
      "type": "",	//类型，S表示单选，M表示多选，T表示文本框
      "queContent": "",
      "options": [
        {
          "id": 0,
          "content": "",
          "isSelect": 0,
          "percentage":""
          "type": "",
          "other": ""
        },	
      ]
    },
    {//文本题
		question:""
		id:""//题号
		type:""// 类型 ----文本框
		content:''//文本内容
	}
  ]
}
```

  





- 权重


  - 获取权重：/selab/vote/weight/get {get}

  - 修改权重:  /selab/vote/weight/modify {post}

    ```
    {
    	voteManager: " "  	//投票管理员
    	DEVLeader: " "  	//开发组组长
    	CSLeader: " "  		//网安组组长
    	DEVManager: " "  	//开发组管理员
    	CSManager: " "  	//网安组管理员
    	DEVMember: " "  	//开发组成员
    	CSMember: " "   	//网安组成员
    }
