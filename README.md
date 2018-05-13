# EsCanalSync
elasticsearch canal table sync client

this is a simple [canal](https://github.com/alibaba/canal/wiki/ClientExample) client for elastic data sync.Just define your mapping object and it can run as a background job.

    - Site search
    - Data aggregation
    - etc.

The repo contains the deploy context and source code;

## [How to Use]

### 1.Add your table object

refer:src/main/java/com/alibaba/otter/index/UserInfo.java

the field with `TableField` annotation and `name` attr,the attr is the real table column name.You'd beatter use `String` type to avoid complicate type conversion.

There is three abstract func:

```
public String getId()
//return the field for elastic index `_id`
```

```
public String getIndexName()
//return the index name sync. to
```

```
public String getTypeName()
//return the type name sync. to
```


For  example:
```
@TableField( name = "id" )
private String userId;

...

@Override
public String getId() {
    // TODO Auto-generated method stub
    return this.userId;
}

@Override
public String getIndexName() {
    // TODO Auto-generated method stub
    return "e0_search_category";
}

@Override
public String getTypeName() {
    // TODO Auto-generated method stub
    return "user";
}

```


### 2.Deploy you client

First,build(maven install) your project while maven-jar-plugin.Then you will git a jar like `canal.sample-1.0-SNAPSHOT.jar` or some name you specified.

Second,cp the jar and dependencies(pom.xml) into `EsCanalClient` dir. Compress the dir as a executable client.

### 3.Config your client and run it.

conf: EsCanalSync/EsCanalClient/conf

You should set your own canal/elastic connection configuration and the api request parameters.

start:EsCanalClient/bin/startup.sh

stop: EsCanalClient/bin/stop.sh
 
