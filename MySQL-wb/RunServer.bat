@echo off
color a
title "MySQL Server Initializing..."
cls
cd "C:\Program Files\MySQL\MySQL Server 8.0\bin\"
mysqld.exe --initialize --console
pause
color B
title "MySQL Server Running..."
cls
mysqld.exe --console --init-file="C:\Program Files\MySQL\MySQL Server 8.0\bin\ResetRootPwd.txt"
mysqld.exe --console
pause