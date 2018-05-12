@echo off 
setlocal enabledelayedexpansion 
if %~n0==1 (
powercfg -x -monitor-timeout-ac 30 
ren 1.bat 30.bat
) ELSE if %~n0==30 (
powercfg -x -monitor-timeout-ac 1 
ren 30.bat 1.bat
)
