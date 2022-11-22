# 投票系统

## 功能需求

### 发起投票

1. 用户可以自定义自己投票的`投票结构（单个问题，多个问题）`和`投票组件（单选，多选，文本框）`
2. 在页面中应该拥有删除`投票组件`的功能
3. 当一名用户发起了一个投票时，所有人应当显示此投票为`新投票`，方便组织进行投票活动

### 我的投票

1. 应该显示用户自己的历史投票内容
2. 投票应该明显的显示其`状态（进行中，结束，弃用）`
3. 应该显示结束时间
4. 最新发起的投票应该在列表最上方
5. 保证安全性
6. 权重需要计算并且返回（可写可不写）
7. 支持撤回操作
8. 提前结束功能

### 查看历史结果

1. 投票应该明显的显示其状态（进行中，结束，弃用）
2. 应当全部匿名（不论前后端），数据传输和保存过程中不允许明文储存用户的信息
3. 应该显示结束时间
4. 最新发起的投票应该在列表最上方
5. 权重需要计算并且返回

### 参与投票（设计时保证代码开闭原则）

1. 每个不同的角色应该拥有不同的投票权重（只针对单选或多选的问题）
2. 投票的策略是管理员全局设置的
3. 当一个投票正在进行时，管理员修改全局策略，此投票应该按开始时的策略继续进行
4. 每次投票结束后，其结果不受后续的策略变动影响
5. 是否能参与投票的时间判断应该由服务器时间为主

### 管理投票策略

1. 仅投票管理员可见
2. 可以切换投票的策略

## 接口约束

- 发起投票: /selab/vote/initiate {post}

  > 访问参数

  {

  ​	"title": "优秀个人选拔",

  ​	"content": "为了奖励那些积极学习的同学，我们要选拔优秀的同学，发放奖励",

  ​	"deadline": "2022-11-11",

  ​	"options": [

  ​		{

  ​			"id": 1,

  ​			"parentId": 0,

  ​			"type": "Q",

  ​			"content": "你认为优秀的个人是:"

  ​		},

  ​		{

  ​			"id": 2,

  ​			"parentId": 1,

  ​			"type": "S",

  ​			"content": "张三"

  ​		},

  ​		{

  ​			"id": 3,

  ​			"parentId": 1,

  ​			"type": "S",

  ​			"content": "李四"

  ​		},

  ​		{

  ​			"id": 4,

  ​			"parentId": 0,

  ​			"type": "Q",

  ​			"content": "请说说为什么选择这个同学？"

  ​		},

  ​		{

  ​			"id": 5,

  ​			"parentId": 4,

  ​			"type": "T",

  ​			"content": ""

  ​		}

  ​	]

  }

  > 返回数据

  {

  ​	"code": 200,

  ​	"msg": "OK"

  }
- 发起投票: /selab/vote/launch {post}


- 参与投票: /selab/vote/commit {post}
- 获取可参与的（未过期）投票粗略信息: /selab/vote/commit/list {get}
- 返回某次投票详细信息: /selab/vote/commit/allInfo/:voteId {get}


- 历史投票
- 返回历史投票信息粗略列表: /selab/vote/commit/list {get}
- 返回某次投票详细信息: /selab/vote/commit/allInfo/:voteId {get}


- 我的投票
- 返回我发起过的投票信息列表: /selab/vote/mine/launched/list {get}
- 返回我发起过的投票详细信息: /selab/vote/mine/launched/:voteId {get}
- 我参与的投票信息列表: /selab/vote/mine/joined/list {get}
- 我参与的投票详细信息: /selab/vote/mine/joined {get}


- 权重
- 获取权重：/selab/vote/weight/get {get}
- 修改权重:  /selab/vote/weight/modify {post}


- 撤回
- 撤回某一条投票: /selab/vote/delete/:voteId {delete}