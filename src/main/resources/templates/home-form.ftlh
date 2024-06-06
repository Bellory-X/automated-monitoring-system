<!DOCTYPE html>
<html lang="en">
<head>HOME PAGE<title>Home Page</title></head>
<body>
    <h1>Admin Page</h1>
    <hr>
    <form action="/form" method="get">
        Search URL: <label><input type="text" name="url"/></label><br><br>
        <input type="submit" value="Open Edit Form">
    </form>
    <hr>
    <form action="/delete" method="post">
        Delete URL: <label><input type="text" name="url"/></label><br><br>
        <input type="submit" value="Delete">
    </form>
    <hr>
    <form action="/email/send" method="post">
        <input type="submit" value="Send Email To Secretary">
    </form>
    <hr>
    <h4>Deleted web page urls</h4>
    <#list deleted_page_urls as url>
    <div><p><b>${url}</b></p></div>
    <#else>
    <h3>Not found</h3>
    </#list>
    <hr>
    <h4>Updated web page urls</h4>
    <#list updated_page_urls as url>
        <div><p><b>${url}</b></p></div>
    <#else>
        <h3>Not found</h3>
    </#list>
    <hr>
    <h4>Created web page urls</h4>
    <#list created_page_urls as url>
        <div><p><b>${url}</b></p></div>
    <#else>
        <h3>Not found</h3>
    </#list>
    <hr>
    <h4>Unmodified web page urls</h4>
    <#list unmodified_page_urls as url>
        <div><p><b>${url}</b></p></div>
    <#else>
        <h3>Not found</h3>
    </#list>
</body>
</html>