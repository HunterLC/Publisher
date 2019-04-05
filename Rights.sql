/*
Navicat SQL Server Data Transfer

Source Server         : DBtest
Source Server Version : 140000
Source Host           : :1433
Source Database       : publisher
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 140000
File Encoding         : 65001

Date: 2019-04-05 16:21:32
*/


-- ----------------------------
-- Table structure for Rights
-- ----------------------------
DROP TABLE [dbo].[Rights]
GO
CREATE TABLE [dbo].[Rights] (
[id] int NOT NULL IDENTITY(1,1) ,
[rightNO] int NULL ,
[rightName] varchar(20) NULL ,
[Module] varchar(20) NULL ,
[W_name] varchar(200) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Rights]', RESEED, 9)
GO

-- ----------------------------
-- Records of Rights
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Rights] ON
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'1', N'1001', N'选题管理', N'编务管理', N'W_xtgl')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'2', N'1002', N'报批查询', N'编务管理', N'W_bpcx')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'3', N'1003', N'组稿查询', N'编务管理', N'W_zgcx')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'4', N'2001', N'图书定价', N'出版管理', N'W_tsdj')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'5', N'2002', N'计划发排', N'出版管理', N'W_jhfp')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'6', N'2003', N'印刷装订', N'出版管理', N'W_yszd')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'7', N'2004', N'结算处理', N'出版管理', N'W_jscl')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'8', N'3001', N'新书入库', N'储运管理', N'W_xsrk')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'9', N'3002', N'盘点入库', N'储运管理', N'W_pdrk')
GO
GO
SET IDENTITY_INSERT [dbo].[Rights] OFF
GO

-- ----------------------------
-- Indexes structure for table Rights
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Rights
-- ----------------------------
ALTER TABLE [dbo].[Rights] ADD PRIMARY KEY ([id])
GO
