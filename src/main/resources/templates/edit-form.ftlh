<#import "ui.ftl" as ui/>

<!DOCTYPE html>
<html lang="en">
<head>EDIT PAGE<title>Edit Page</title></head>
<body>
    <div>
        <fieldset>
            <legend>Edit page</legend>
            <form name="form" action="/form/add" method="post">
                <@ui.formInput id="t1" name="url" label="URL" value="${form.url}"/> <br/>
                <@ui.formTextarea id="t2" name="content" label="Content" value="${form.content}"/> <br/>
                <input type="submit" value="Save"/>
            </form>
        </fieldset>
    </div>
</body>
</html>