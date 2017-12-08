## OrangeMsg

消息中心接口文档包括 **消息发送** 部分和 **消息接收** 部分，消息发送部分主要针对后端业务触发消息，如变更课程提醒；消息接收部分主要针对前端数据显示，如个人消息盒子等功能，主要提供了消息分页查看及查看消息详情等功能性接口。

## 全局说明

- 时间戳精确到秒

## 1 消息发送

消息发送接口均为**异步操作**，返回结果仅表示任务提交成功；请求需指定topic以确定消息分类，目前仅有日志log、业务business和通知notice三种；相应业务接口通过消息动作代码触发；

消息动作代码列举如下：

```
//notice 相关
public static final String ACTION_NOTICE_CREATE = "notice_create";              //发送系统通知
public static final String ACTION_NOTICE_PULL = "notice_pull";                  //拉取系统通知

//business 相关
public static final String ACTION_BUSINESS_CREATE = "business_create";          //发送业务消息

//log 相关
public static final String ACTION_LOG_CREATE = "log_create";                    //发送系统通知
```

### 1.1 消息格式

消息发送有两种方式，kafka发送和http接口发送。

#### kafka发送

消息体为json格式，其属性如下：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
uuid        | 消息唯一编号，重复发送将被丢弃                | 是
channel     | 消息渠道  短信、推送、IM等                    | 否
type        | 消息类别  文本 、图文、语音等，默认文本       | 否
action      | 消息动作代码 发送通知、拉取通知等             | 是
data        | 业务数据                                      | 是

消息体（保存操作日志）完整示例：

```
{
    "uuid": "123456789",
    "channel": "sms",
    "type": "text",
    "action": "log_create",
    "data": {
        action: "system_sa_login",      //业务类型
        content: "张三登录了系统",      //日志内容
        operUid: 10024,                 //操作用户id
        operName: "张三",               //操作人姓名
        operTime: 1504668049            //操作时间戳，非必填，默认当前时间
    }
}
```


#### http接口发送

**请求**

- 请求地址：    *http://域名/place_message*

- 请求方式：POST

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
topic       | 消息主题，目前仅有log、notice、business三种   | 是
value       | 消息体json，参见kafka消息体描述               | 是
key         | 消息键                                        | 否
timestamp   | 消息时间戳                                    | 否
partition   | kafka分区                                     | 否


**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"发送成功"	        //状态描述信息
}
```

以下接口仅对消息体data属性进行说明

### 1.2 保存操作日志

**请求**

- TOPIC：   *log*
- ACTION：   *log_create*
- DATA：

```
{
    action: "system_sa_login",      //业务类型
    content: "张三登录了系统",      //日志内容
    operUid: 10024,                 //操作用户id
    operName: "张三",               //操作人姓名
    operTime: 1504668049            //操作时间戳，非必填，默认当前时间
}
```

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"发送成功"	        //状态描述信息
}
```

### 1.3 发送业务消息

**请求**

- TOPIC：   *business*
- ACTION：   *business_create*
- DATA：

```
{
    targets:[
        {
            targetUid:10000,                //接收人
            targetName:"李四"               //接收人姓名
        },
        {
            targetUid:10001,                //接收人
            targetName:"王五"               //接收人姓名
        }
    ],
    operUid: 10024,                         //操作用户id
    operName: "张三",                       //操作人姓名
    operTime: 1504668049,                   //操作时间戳，非必填，默认当前时间
    content: "消息内容"   //消息内容
}
```

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"发送成功"	        //状态描述信息
}
```

### 1.4 发送系统通知

**请求**

- TOPIC：   *notice*
- ACTION：   *notice_create*
- DATA：

```
{
    content: "张三登录了系统",              //通知内容
    operTime: 1504668049,                   //操作时间戳，非必填，默认当前时间
    scope:1                                 //通知范围 1.全局
}
```

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"发送成功"	        //状态描述信息
}
```

### 1.5 系统通知同步

因系统通知需进行全局推送，用户过多时压力较大，所以采取客户端主动拉取模式（后期根据需要考虑是否开发自动同步）

**请求**

- TOPIC：   *notice*
- ACTION：   *notice_pull*
- DATA：

```
{
    targetUid: 10000,                       //接收人
    targetName: "李四",                     //接收人姓名
}
```

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"发送成功"	        //状态描述信息
}
```

### 1.6 发送模版消息（开发中）

模版消息用于发送固定格式的业务消息，需统一定制模版，并预设占位符，占位符格式为 **${key1}**，传递的业务数据应与占位符保持对应。

**请求**

- TOPIC：   *business*
- ACTION：   *business_temp_create*
- DATA：

```
{
    operUid: 10024,                         //操作用户id
    operName: "张三",                       //操作人姓名
    operTime: 1504668049,                   //操作时间戳，非必填，默认当前时间
    targets:[
        {
            targetUid:10000,                //接收人
            targetName:"李四"               //接收人姓名
        },
        {
            targetUid:10001,                //接收人
            targetName:"王五"               //接收人姓名
        }
    ],
    templateId:"59cc1bcf73652013d4d16951",  //模版id
    values:{                                //模版填充数据，key1和value1并不是真正的键与值，请替换为占位符的键
        key1:value1,
        key2:value2,
        ...
    }
}
```

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"发送成功"	        //状态描述信息
}
```

## 2 消息获取

---

### 2.1 新消息通知

用于监控新消息，需轮询请求

**请求** 

- 请求地址：*http://域名/receive_message/{id}*

- 请求方式：GET

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"发送成功",	        //状态描述信息
	content:[
	    {
	        type: "business",
	        uuid: "123456789",
	        content:"消息内容"
	    },
	    {
	        type: "notice",
	        uuid: "245876133",
	        content:"消息内容"
	    }
	]
	totalBusinesses:1,      //业务消息总条数
	totalNotices:1,         //系统通知总条数
	totalElements:2         //总条数
	
}
```

### 2.2 业务消息列表

**请求** 

- 请求地址：*http://域名/business/{id}*

- 请求方式：GET

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是
page        | 第几页，从0开始，默认为第0页                  | 否
size        | 分页条数，默认为20                            | 否

**返回**

```
{
    "content": [
        {
            uuid: "123456789",                      //消息id
            operTime: 1504668049,                   //操作时间戳
            targetUid: 10000,                       //接收人
            targetName: "李四",                     //接收人姓名
            status: 2                               //读取状态 1.未读 2.已读
            business:{
                operUid: 10024,                         //操作用户id
                operName: "张三",                       //操作人姓名
                operTime: 1504668049,                   //操作时间戳
                content: "消息内容。"
            }
        }
    ],
    "totalElements": 1,             //总条数
    "totalPages": 1,                //总页数
    "last": true,                   //是否最后一页
    "number": 0,                    //当前页码，从0开始
    "size": 20,                     //分页条数
    "sort": null,                   //排序条件
    "first": true,                  //是否第一页
    "numberOfElements": 1           //当前页条数
}
```

### 2.3 业务消息详情

**请求** 

- 请求地址：*http://域名/business/{id}/{uuid}*

- 请求方式：GET

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是
uuid        | 消息uuid                                      | 是

**返回**

```
{
    uuid: "123456789",                      //消息id
    operTime: 1504668049,                   //操作时间戳
    targetUid: 10000,                       //接收人
    targetName: "李四",                     //接收人姓名
    status: 2                               //读取状态 1.未读 2.已读
    business:{
        operUid: 10024,                         //操作用户id
        operName: "张三",                       //操作人姓名
        operTime: 1504668049,                   //操作时间戳
        content: "消息内容。"
    }
}
```

### 2.4 业务消息设为已读

**请求** 

- 请求地址：*http://域名/business/{id}/{uuid}*

- 请求方式：PUT

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是
uuid        | 消息uuid                                      | 是

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"成功"  	        //状态描述信息
}
```

### 2.5 系统通知列表

查询用户通知前需调用 **1.5** 或 **2.7** 接口，将通知消息拉取至个人库（用于标识已推送、已读等）

**请求** 

- 请求地址：*http://域名/notice/{id}*

- 请求方式：GET

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是
page        | 第几页，从0开始，默认为第0页                  | 否
size        | 分页条数，默认为20                            | 否

**返回**

```
{
    "content": [
        {
            "uuid": "123456789",                    //消息id
            "action": "read",                       //操作动作 dispatch.分发 read.读取
            "targetUid": 10000,                     //接收人
            "targetName": null,                     //接收人姓名
            "operTime": 1504781022,                 //消息时间戳
            "status": 1,                            //读取状态 1.未读 2.已读
            "notice": {
                "uuid": "123456789",
                "action": "system_sa_login",
                "targetUid": 10000,
                "targetName": "李四",
                "content": "消息内容。",
                "operTime": 1504781022,
                "scope": null
            }
        }
    ],
    "totalElements": 1,             //总条数
    "totalPages": 1,                //总页数
    "last": true,                   //是否最后一页
    "number": 0,                    //当前页码，从0开始
    "size": 20,                     //分页条数
    "sort": null,                   //排序条件
    "first": true,                  //是否第一页
    "numberOfElements": 1           //当前页条数
}
```

### 2.6 系统通知详情

**请求** 

- 请求地址：*http://域名/notice/{id}/{uuid}*

- 请求方式：GET

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是
uuid        | 消息uuid                                      | 是

**返回**

```
{
    "uuid": "123456789",                    //消息id
    "action": "read",                       //操作动作 dispatch.分发 read.读取
    "targetUid": 10000,                     //接收人
    "targetName": null,                     //接收人姓名
    "operTime": 1504781022,                 //消息时间戳
    "status": 1,                            //读取状态 1.未读 2.已读
    "notice": {
        "uuid": "123456789",
        "action": "system_sa_login",
        "targetUid": 10000,
        "targetName": "李四",
        "content": "消息内容。",
        "operTime": 1504781022,
        "scope": null
    }
}
```

### 2.7 系统通知设为已读

**请求** 

- 请求地址：*http://域名/notice/{id}/{uuid}*

- 请求方式：PUT

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是
uuid        | 消息uuid                                      | 是

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"成功"  	        //状态描述信息
}
```

### 2.8 系统通知同步数据

此接口与1.4功能相同，非异步请求，轮询间隔建议5分钟，未压测

**请求** 

- 请求地址：*http://域名/notice/sync/{id}*

- 请求方式：GET

- 接口参数：

参数名称    | 描述                                          | 必填
---         | ---                                           | ---
id          | 用户id                                        | 是

**返回**

```
{
	status:0,               //0 成功 非0 失败
	msg:"成功",  	        //状态描述信息
	totalElements:1         //同步条数
}
```
