<body>
<h1>${developer}</h1>
<h2><font size="4" color="red">Developer Tasks</font></h2>
<#list tasksref as task>
    <p> ${task.task} </p>
</#list>
<h3><font size="4" color="red">Developer points</font></h3>
<p>Total ${totalPoints} points per ${sprints} sprints</p>
<p>Average ${average} over ${sprints} sprints</p>
</body>

