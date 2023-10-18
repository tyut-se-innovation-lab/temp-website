# Temp-website Vote Module

**简介**:投票模块接口文档


**HOST**:http://localhost:8080

**联系人**:lizhichao

**Version**: 1.0 

**介绍：** 返回的实体类属性介绍和枚举类都在最后，请求参数在各个请求里都有说明


**接口路径**:/v3/api-docs









# 参与投票人员


## 查看所有能投票的人员

**接口地址**:`/vote/showPeople/{voteId}`

**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`

**接口描述**: 

**请求参数**: 


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|

**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|||||


**响应示例**:
```javascript
{
  "msg": "操作成功",
  "code": 200,
  "data": 
  [{
  "voteId": 0,
  "userName": "String",
  "isComplete": 0     // 是否完成投票 1为完成 0为未完成
   }...]
}
```


# 管理员投票细节展示


## 查看投票细节 【目前暂定发起人和管理员都可以实时看到投票信息，也就是都发这个请求↔️】

**接口地址**:`/vote/result/showDetails/{voteId}`

**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:

**请求参数**:

暂无

**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|

**响应参数**:

==data : VoteResultDetails==

**响应示例**:

```javascript
{
  "msg": "操作成功",
  "code": 200,
  "data": {
    "id": null,
    "username": null,
    "userId": null,
    "type": null,
    "title": null,
    "content": null,
    "status": null,
    "createTime": null,
    "deadTime": null,
    "voteOptionDetails": null,
    "voteReports": null,
    "voteWeights": null
  }
}
```


# 投票举报信息


## 查看举报信息

**接口地址**:`/vote/report/view/{voteId}`


**请求方式**:`POST`

**请求数据类型**:`application/x-www-form-urlencoded`

**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|voteId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|List<VoteReport>|详情见👇|      ||


**响应示例**:
```javascript
{
	"empty": true
}
```


## 提交举报信息


**接口地址**:`/vote/report/submit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "reportId": 0, 为空
  "user_id": 0,  为空
  "voteId": 0,
  "description": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|voteReport|投票举报|body|true|VoteReport|VoteReport|
|&emsp;&emsp;reportId|主键ID||false|integer(int64)||
|&emsp;&emsp;user_id|举报人ID||false|integer(int64)||
|&emsp;&emsp;voteId|投票ID||false|integer(int64)||
|&emsp;&emsp;description|原因简述||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|empty||boolean||


**响应示例**:
```javascript
{
	"empty": true
}
```


# 查询投票


## 查看冻结投票（分页查询）


**接口地址**:`/vote/query/untreated`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无

**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|List<VoteRangeVo>||||

**响应示例**:

```javascript
{
	 见👇 （分页查询下面有统一示例）
}
```


## 查询我举报的投票（分页查询）


**接口地址**:`/vote/query/myreport`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|List<VoteRangeVo>||||


**响应示例**:
```javascript
{
	 见👇
}
```


## 查询我发起的投票（分页查询）


**接口地址**:`/vote/query/mylaunch`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|List<VoteRangeVo>||||


**响应示例**:
```javascript
{
	 见👇
}
```


## 查询投票（分页查询）


**接口地址**:`/vote/query/mycommit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:

 { 

​    "isEnd": Int

​     "isParticipate"： int

}

*未空默认为全部（1为是，0为）*


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|List<VoteRangeVo>||||


**响应示例**:
```javascript
{
	 见👇
}
```


## 通过ID查询投票


**接口地址**:`/vote/query/{voteId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|voteId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|List<VoteRangeVo>|见👇|      ||


**响应示例**:
```javascript
{
	{
	 "msg": "操作成功",
    "code": 200,
     "data":[{},{}]
}
}
```


# 处理投票


## 撤回投票

**接口地址**:`/vote/deal/withdraw`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|empty||boolean||


**响应示例**:
```javascript

	{
	 "msg": "操作成功",
    "code": 200,
}

```


## 发布投票


**接口地址**:`/vote/deal/launch`

**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`

**接口描述**:

注： 发布投票选择权重和角色人群应先发送请求（GET http://192.168.1.134:8080/system/role/list&pageNum=1&pageSize=10）获取实时的角色信息        *这个是另一个模板的内容，他只有一个分页查询接口， 有点恶心🤞 目前先按这个接口写吧*

请求示例：

~~~javascript
{
  "total": 8,
  "rows": [
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 09:08:52",
      "updateBy": null,
      "updateTime": null,
      "remark": "超级管理员",
      "params": {},
      "roleId": 1,
      "roleName": "超级管理员",
      "roleKey": "admin",
      "roleSort": "1",
      "dataScope": "1",
      "menuCheckStrictly": true,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": true
    },
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 14:53:45",
      "updateBy": null,
      "updateTime": null,
      "remark": "管理实验室投票系统的职位",
      "params": {},
      "roleId": 100,
      "roleName": "投票管理员",
      "roleKey": "vote-leader",
      "roleSort": "2",
      "dataScope": "1",
      "menuCheckStrictly": false,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": false
    },
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 14:55:37",
      "updateBy": null,
      "updateTime": null,
      "remark": "实验室开发组组长",
      "params": {},
      "roleId": 101,
      "roleName": "开发组组长",
      "roleKey": "software-development-leader",
      "roleSort": "3",
      "dataScope": "1",
      "menuCheckStrictly": true,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": false
    },
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 14:55:37",
      "updateBy": null,
      "updateTime": null,
      "remark": "实验室开发组管理员",
      "params": {},
      "roleId": 102,
      "roleName": "开发组管理员",
      "roleKey": "software-development-executive",
      "roleSort": "4",
      "dataScope": "1",
      "menuCheckStrictly": false,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": false
    },
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 14:55:37",
      "updateBy": null,
      "updateTime": null,
      "remark": "实验室网安组管理员",
      "params": {},
      "roleId": 103,
      "roleName": "网安组组长",
      "roleKey": "network-security-leader",
      "roleSort": "5",
      "dataScope": "1",
      "menuCheckStrictly": true,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": false
    },
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 14:55:37",
      "updateBy": null,
      "updateTime": null,
      "remark": "实验室网安组管理员",
      "params": {},
      "roleId": 104,
      "roleName": "网安组管理员",
      "roleKey": "network-security-executive",
      "roleSort": "6",
      "dataScope": "1",
      "menuCheckStrictly": true,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": false
    },
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 14:57:15",
      "updateBy": null,
      "updateTime": null,
      "remark": "实验室成员",
      "params": {},
      "roleId": 105,
      "roleName": "实验室开发组成员",
      "roleKey": "software-development-member",
      "roleSort": "7",
      "dataScope": "1",
      "menuCheckStrictly": false,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": false
    },
    {
      "searchValue": null,
      "createBy": null,
      "createTime": "2022-09-25 14:57:15",
      "updateBy": null,
      "updateTime": null,
      "remark": "实验室成员",
      "params": {},
      "roleId": 106,
      "roleName": "实验室网安组成员",
      "roleKey": "network-security-member",
      "roleSort": "8",
      "dataScope": "1",
      "menuCheckStrictly": false,
      "deptCheckStrictly": true,
      "status": "0",
      "delFlag": "0",
      "flag": false,
      "menuIds": null,
      "deptIds": null,
      "permissions": null,
      "admin": false
    }
  ],
  "code": 200,
  "msg": "查询成功"
}
~~~

**请求示例**:


```javascript
{
  "id": 0,    空
  "username": "",   空
  "userId": 0,   空
  "type": "",
  "title": "",
  "content": "",
  "status": "",   空
  "createTime": "", 空
  "deadTime": "",
  "optionNum": 0,  
  "isRealTime": "",
  "voteOptionVoList": [
    {
      "id": 0,    空
      "voteId": 0,   空
      "optionType": "",
      "content": "",
    }
  ],
  "isComplete": 0, 空
  "voteWeights": [
    {
      "roleId": 0,
      "roleName": "", 空
      "weight": 0
    }
  ],
  "delFlag": "" 空
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|voteInfo|投票信息|body|true|VoteInfo|VoteInfo|
|&emsp;&emsp;id|主键ID||false|integer(int64)||
|&emsp;&emsp;username|发起人||false|string||
|&emsp;&emsp;userId|||false|integer(int64)||
|&emsp;&emsp;type|投票类型,可用值:1,2,3,100||false|string||
|&emsp;&emsp;title|投票标题||false|string||
|&emsp;&emsp;content|投票介绍||false|string||
|&emsp;&emsp;status|投票状态,可用值:0,1,2,3,4||false|string||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;deadTime|截止时间||false|string(date-time)||
|&emsp;&emsp;optionNum|选择个数||false|integer(int32)||
|&emsp;&emsp;isRealTime|投票是否实时||false|string||
|&emsp;&emsp;voteOptionVoList|投票选项||false|array|VoteOptionVo|
|&emsp;&emsp;&emsp;&emsp;id|主键ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;voteId|投票ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;optionType|选项类型,可用值:S,C||false|string||
|&emsp;&emsp;&emsp;&emsp;content|选项内容||false|string||
|&emsp;&emsp;&emsp;&emsp;percentage|百分比||false|number||
|&emsp;&emsp;&emsp;&emsp;voteNum|票数||false|integer||
|&emsp;&emsp;&emsp;&emsp;isSelect|||false|boolean||
|&emsp;&emsp;isComplete|是否已完成||false|integer(int32)||
|&emsp;&emsp;voteWeights|投票权重||false|array|VoteWeight|
|&emsp;&emsp;&emsp;&emsp;roleId|角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleName|角色名称||false|string||
|&emsp;&emsp;&emsp;&emsp;weight|权重值||false|integer||
|&emsp;&emsp;delFlag|删除标志||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|empty||boolean||


**响应示例**:
```javascript
{
	"empty": true
}
```


## 处理冻结投票


**接口地址**:`/vote/deal/handle/{voteId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`

**响应数据类型**:`*/*`


**接口描述**:

**请求参数**:,   


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|voteId||path|true|integer(int64)||
|handel|handel: 0/1  0为关闭 1 为 恢复正常|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|empty||boolean||


**响应示例**:
```javascript
{
	"empty": true
}
```


## 投票结束后删除投票


**接口地址**:`/vote/deal/delete/{voteId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|voteId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|empty||boolean||


**响应示例**:
```javascript
{
	"empty": true
}
```


# 提交投票


## 进行投票


**接口地址**:`/vote/commit/dovote`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,  空
  "voteOptionId": 0,
  "userId": "",  空
  "content": "",
  "createTime": "" 空
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|voteResult|投票结果|body|true|VoteResult|VoteResult|
|&emsp;&emsp;id|主键ID||false|integer(int64)||
|&emsp;&emsp;voteOptionId|投票选项ID||false|integer(int64)||
|&emsp;&emsp;userId|投票人ID||false|string||
|&emsp;&emsp;content|投票内容||false|string||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|empty||boolean||


**响应示例**:
```javascript
{
	"empty": true
}
```


# 


## 获取投票模块消息


**接口地址**:`/vote/reminder/reminder`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|AjaxResult|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|empty||boolean||


**响应示例**:
```javascript
{
	"empty": true
}
```

## 枚举类展示

> 枚举类响应是只返回第一个参数

#### VoteOptionType

 

| sign(String) | remake |
| :----------: | ------ |
|      S       | 单选框 |
|      C       | 文本域 |

#### VoteReminderType

| sign | remake         |
| :--: | -------------- |
|  1   | 举报数量上限冻结 |
|  2   | 投票被举报     |
|  3   | 撤回投票提醒   |
|  4   | 待参与投票提醒 |
|  5   | 冻结处理结果提醒 |
|  6   | 投票即将结束提醒 |
|  7   | 投票结束提醒 |

#### VoteStatus

|  id  |  remake  |
| :--: | :------: |
|  1   |  进行中  |
|  2   |  已结束  |
|  3   | 异常冻结 |
|  4   |  被撤回  |
|  5   |  已关闭  |

#### VoteType

| id   | remake                                    |
| ---- | ----------------------------------------- |
| 1    | 控诉管理人员                              |
| 2    | 征求意见 （目前先开发这个和投票俩种类型） |
| 3    | 投票                                      |
| 100  | 自定义类型                                |

## 实体类展示

#### VoteInfo 投票信息

| 参数名称         | 参数类型                | 参数说明     |
| ---------------- |---------------------| ------------ |
| voteId           | Long                | 主键ID       |
| userName         | String              | 发起人       |
| voteType         | VoteType            | 投票类型     |
| title            | String              | 投票标题     |
| content          | String              | 投票介绍     |
| status           | VoteStatus          | 投票状态     |
| createTime       | Date                | 创建时间     |
| deadTime         | Date                | 截止时间     |
| optionNum        | Integer             | 选择个数     |
| isRealTime       | String              | 投票是否实时 |
| voteOptionLaunchDTOs | List<VoteOptionDTO> | 投票选项     |
| isComplete       | Integer             | 是否已完成   |
| voteWeights      | List<VoteWeight>    | 投票权重     |
| delFlag          | String              | 删除标志     |

#### VoteInfoLaunchDTO 投票信息

| 参数名称         | 参数类型           | 参数说明     |
| ---------------- | ---------------- | ------------ |
| voteId           | Long             | 主键ID       |
| userName         | String           | 发起人       |
| voteType         | VoteType         | 投票类型     |
| title            | String           | 投票标题     |
| content          | String           | 投票介绍     |
| status           | VoteStatus       | 投票状态     |
| createTime       | Date             | 创建时间     |
| deadTime         | Date             | 截止时间     |
| optionNum        | Integer          | 选择个数     |
| isRealTime       | String           | 投票是否实时 |
| voteOptionList | List<VoteOption> | 投票选项     |
| isComplete       | Integer          | 是否已完成   |
| voteWeights      | List<VoteWeight> | 投票权重     |
| delFlag          | String           | 删除标志     |
### VoteOption投票选项

| 参数名称   | 参数类型       | 参数说明 |
| ---------- | -------------- | -------- |
| id         | Long           | 主键ID   |
| voteId     | Long           | 投票ID   |
| optionType | VoteOptionType | 选项类型 |
| content    | String         | 选项内容 |

#### VoteReport投票举报

| 参数名称    | 参数类型 | 参数说明 |
| ----------- | -------- | -------- |
| reportId    | Long     | 主键ID   |
| user_id     | Long     | 举报人ID |
| voteId      | Long     | 投票ID   |
| description | String   | 原因简述 |

## VoteResult投票结果

| 参数名称     | 参数类型 | 参数说明                      |
| ------------ | -------- | ----------------------------- |
| id           | Long     | 主键ID                        |
| voteId       | Long     | 投票ID                        |
| voteOptionId | Long     | 投票选项ID                    |
| userName     | String   | 投票人姓名                    |
| userId       | String   | 投票人ID                      |
| content      | String   | 投票内容                      |
| createTime   | Date     | 创建时间                      |
| isAnonymous  | String   | 是否实名（1 为匿名 0 为实名） |

## VoteResultDetails(投票细节展示)

| 参数名称              | 参数类型                    | 参数说明             |
|-------------------|-------------------------|------------------|
| voteId            | Long                    | 主键ID             |
| userName          | String                  | 发起人              |
| userId            | Long                    | 发起人ID            |
| voteType          | VoteType                | 投票类型             |
| title             | String                  | 投票标题             |
| content           | String                  | 投票介绍             |
| status            | VoteStatus              | 投票状态             |
| createTime        | Date                    | 创建时间             |
| deadTime          | Date                    | 截止时间             |
| voteOptionDetails | List<VoteOptionDetails> | 投票选项             |
| isRealTime        | String                  | 投票是否实时（1为实时，0为否） |
| voteWeights       | List<VoteWeight>        | 投票权重             |

#### VoteUser参与投票人

| 参数名称   | 参数类型 | 参数说明                       |
| ---------- | -------- | ------------------------------ |
| voteId     | Long     |                                |
| userName   | String   |                                |
| isComplete | Integer  | 是否完成投票 1为完成 0为未完成 |

#### VoteWeight投票权重

| voteId   | Long    | 投票ID   |
| -------- | ------- | -------- |
| roleId   | Long    | 角色ID   |
| roleName | String  | 角色名称 |
| weight   | Integer | 权重值   |
|          |         |          |

#### VoteOptionDetails投票选项细节

| 参数名称   | 参数类型           | 参数说明         |
| ---------- |----------------| ---------------- |
| id         | Long           | 主键ID           |
| voteId     | Long           | 投票ID           |
| optionType | VoteOptionType | 选项类型         |
| content    | String         | 选项内容         |
| percentage | String         | 该选项百分比     |
| voteNum    | Integer        | 该选项票数       |
| userName   | List<String>   | 选择当前选项人员 |

#### VoteOptionDTO

| 参数名称   | 参数类型           | 参数说明 |
| ---------- |----------------| -------- |
| id         | Long           | 主键ID   |
| voteId     | Long           | 投票ID   |
| optionType | VoteOptionType | 选项类型 |
| content    | String         | 选项内容 |
| percentage | String         | 百分比   |
| voteNum    | Integer        | 票数     |
| isSelect   | Boolean        | 是否选中 |

#### VoteRangeVo投票粗略信息

| 参数名称   | 参数类型   | 参数说明 |
| ---------- | ---------- | -------- |
| voteId     |            |          |
| title      |            |          |
| content    |            |          |
| voteStatus | VoteStatus |          |
| createTime |            |          |
| deadTime   |            |          |
| userName   |            |          |
| userId     |            |          |

#### VoteReminderDto(投票消息)

| 参数名称         | 参数类型         | 参数说明 |
| ---------------- | ---------------- | -------- |
| voteId           |                  |          |
| title            |                  |          |
| content          |                  |          |
| voteStatus       | VoteStatus       |          |
| createTime       |                  |          |
| deadTime         |                  |          |
| userName         |                  |          |
| userId           |                  |          |
| voteReminderType | VoteReminderType |          |

## 分页查询返回示例

~~~javascript
{
  "total": 8,
  "rows": [
    { }
  ],
  "code": 200,
  "msg": "查询成功"
}
~~~

