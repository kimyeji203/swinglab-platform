#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys , re
__IS_VERSION_3__= sys.version_info.major == 3


con_schema = "public"

# 개발
con_opts = {
    'user' : 'swinglab'
    ,'password' : 'qhdks@00'
    ,'host'  : '13.125.190.65'
    ,'port' : 5432
    ,'database' : 'swinglab'
}


# cmp_cons = [
#     {
#         'user' : 'postgres'
#         ,'password' : 'qhdks@00'
#         ,'host'  : '192.168.10.204'
#         ,'port' : 5432
#         ,'database' : 'payment'
#     },
#     {
#         'user' : 'postgres'
#         ,'password' : 'qhdks@00'
#         ,'host'  : '192.168.10.207'
#         ,'port' : 5432
#         ,'database' : 'payment'
#     }
# ]


ENUM_PACKAGE = 'com.macrogen.MacrogenCodes'

######################################################################################
ENUM_TYPE_INTERFACE_PACKAGE = {} # enum_name = interface_package

# event
ENUM_TYPE_INTERFACE_PACKAGE['INST_TYPE'] = ['com.kt.gsims.base.monitoring.fault.domain.InstanceType']

######################################################################################
FIELD_NAME_ENUM_TYPES = {} # column_name = enum_name

FIELD_NAME_ENUM_TYPES['ACNT_STTS_CD'] = 'ACNT_STTS_CD'
FIELD_NAME_ENUM_TYPES['LOGIN_PRVNT_STTS_CD'] = 'LOGIN_PRVNT_STTS_CD'
FIELD_NAME_ENUM_TYPES['MENU_TYPE_CD'] = 'MENU_TYPE_CD'
FIELD_NAME_ENUM_TYPES['SVC_STTS_CD'] = 'SVC_STTS_CD'
FIELD_NAME_ENUM_TYPES['GENDER_TYPE_CD'] = 'GENDER_TYPE_CD'
FIELD_NAME_ENUM_TYPES['OPRTR_STTS_CD'] = 'OPRTR_STTS_CD'

FIELD_NAME_ENUM_TYPES['DEVICE_TYPE_CD'] = 'DEVICE_TYPE_CD'
FIELD_NAME_ENUM_TYPES['EVENT_REWARD_TYPE_CD'] = 'REWARD_TYPE_CD'
FIELD_NAME_ENUM_TYPES['GAME_TYPE_CD'] = 'GAME_TYPE_CD'
FIELD_NAME_ENUM_TYPES['ITEM_DATA_TYPE_CD'] = 'ITEM_DATA_TYPE_CD'
FIELD_NAME_ENUM_TYPES['DATA_TYPE_CD'] = 'DATA_TYPE_CD'
FIELD_NAME_ENUM_TYPES['MENU_TYPE_CD'] = 'MENU_TYPE_CD'
FIELD_NAME_ENUM_TYPES['ORDER_STATE_CD'] = 'ORDER_STATE_CD'
FIELD_NAME_ENUM_TYPES['PAYMENT_STATE_CD'] = 'PAYMENT_STATE_CD'
FIELD_NAME_ENUM_TYPES['CANCEL_STATE_CD'] = 'CANCEL_STATE_CD'
FIELD_NAME_ENUM_TYPES['REFUND_STATE_CD'] = 'REFUND_STATE_CD'
FIELD_NAME_ENUM_TYPES['RESULT_SHEET_OFFER_CD'] = 'RESULT_SHEET_OFFER_CD'
FIELD_NAME_ENUM_TYPES['EXCHANGE_STATE_CD'] = 'EXCHANGE_STATE_CD'
FIELD_NAME_ENUM_TYPES['DLIVRY_CD'] = 'DLIVRY_CD'
FIELD_NAME_ENUM_TYPES['CURRENCY_CD'] = 'CURRENCY_CD'

FIELD_NAME_ENUM_TYPES['CONTS_TYPE_CD'] = 'CONTS_TYPE_CD'
FIELD_NAME_ENUM_TYPES['EVENT_TYPE_CD'] = 'EVENT_TYPE_CD'
FIELD_NAME_ENUM_TYPES['REWARD_STATE_CD'] = 'REWARD_STATE_CD'
FIELD_NAME_ENUM_TYPES['REWARD_TRGT_CD'] = 'REWARD_TRGT_CD'
FIELD_NAME_ENUM_TYPES['REWARD_TYPE_CD'] = 'REWARD_TYPE_CD'
FIELD_NAME_ENUM_TYPES['REWARD_TIME_CD'] = 'REWARD_TIME_CD'
FIELD_NAME_ENUM_TYPES['REASON_CD'] = 'REASON_CD'
FIELD_NAME_ENUM_TYPES['ORDER_TYPE_CD'] = 'ORDER_TYPE_CD'
FIELD_NAME_ENUM_TYPES['SURVEY_STATE_CD'] = 'SURVEY_STATE_CD'
FIELD_NAME_ENUM_TYPES['SURVEY_TYPE_CD'] = 'SURVEY_TYPE_CD'
FIELD_NAME_ENUM_TYPES['TERMS_USE_LOC_CD'] = 'TERMS_USE_LOC_CD'

FIELD_NAME_ENUM_TYPES['REFUND_TYPE_CD'] = 'REFUND_TYPE_CD'
FIELD_NAME_ENUM_TYPES['PAYMENT_TYPE_CD'] = 'PAYMENT_TYPE_CD'
FIELD_NAME_ENUM_TYPES['DDUCTN_REASON_CD'] = 'DDUCTN_REASON_CD'
FIELD_NAME_ENUM_TYPES['DDUCTN_STATE_CD'] = 'DDUCTN_STATE_CD'
FIELD_NAME_ENUM_TYPES['INCRS_REASON_CD'] = 'INCRS_REASON_CD'
FIELD_NAME_ENUM_TYPES['GOODS_ORDER_TYPE_CD'] = 'GOODS_ORDER_TYPE_CD'
FIELD_NAME_ENUM_TYPES['OPRTR_APV_STTUS_CD'] = 'APV_STTUS_CD'
# FIELD_NAME_ENUM_TYPES['AUTH_METHOD_CD'] = 'AUTH_METHOD_CD'

FIELD_NAME_ENUM_TYPES['SNS_TYPE_CD'] = 'SNS_TYPE_CD'
FIELD_NAME_ENUM_TYPES['EXE_TYPE_CD'] = 'EXE_TYPE_CD'
FIELD_NAME_ENUM_TYPES['HTTP_METHOD_CD'] = 'HTTP_METHOD_CD'
FIELD_NAME_ENUM_TYPES['PGM_ROLE_CD'] = 'PGM_ROLE_CD'
FIELD_NAME_ENUM_TYPES['LOGGING_TYPE_CD'] = 'LOGGING_TYPE_CD'
FIELD_NAME_ENUM_TYPES['GLOBAL_GROUP_TYPE_CD'] = 'GLOBAL_GROUP_TYPE_CD'









FIELD_NAME_ENUM_TYPES['CPN_TYPE_CD'] = 'CPN_TYPE_CD'
FIELD_NAME_ENUM_TYPES['CPN_STTUS_CD'] = 'CPN_STTUS_CD'
# FIELD_NAME_ENUM_TYPES['DSTR_STTUS_CD'] = 'DSTR_STTUS_CD'
# FIELD_NAME_ENUM_TYPES['DSTR_TGT_TYPE_CD'] = 'DSTR_TGT_TYPE_CD'
# FIELD_NAME_ENUM_TYPES['DSTR_POTIM_TYPE_CD'] = 'DSTR_POTIM_TYPE_CD'
# FIELD_NAME_ENUM_TYPES['DSTR_FORML_CD'] = 'DSTR_FORML_CD'
FIELD_NAME_ENUM_TYPES['REG_PERD_SETUP_METH_CD'] = 'PERD_SETUP_METH_CD'
FIELD_NAME_ENUM_TYPES['EFCT_PERD_SETUP_METH_CD'] = 'PERD_SETUP_METH_CD'
FIELD_NAME_ENUM_TYPES['REG_PERD_RSTRTN_UNIT_CD'] = 'TIME_UNIT_CD'
FIELD_NAME_ENUM_TYPES['REWARD_TIME_CD'] = 'REWARD_TIME_CD'

FIELD_NAME_ENUM_TYPES['EMOTION_CD'] = 'EMOTION_CD'

FIELD_NAME_ENUM_TYPES['FAQ_TYPE_CD'] = 'FAQ_TYPE_CD'
FIELD_NAME_ENUM_TYPES['INQUIRY_TYPE_CD'] = 'INQUIRY_TYPE_CD'
FIELD_NAME_ENUM_TYPES['INDCATR_PAIR_CD'] = 'INDCATR_PAIR_CD'


FIELD_NAME_ENUM_TYPES['EMOTION_CD'] = 'EMOTION_CD'

FIELD_NAME_ENUM_TYPES['LOPRTR_CD'] = 'LOPRTR_CD' # 리워드제공조건논리연산자	거짓	LOPRTR_CD	N/A	NOT NULL	BOOLEAN
# REWARD_TIME_CD 교환상태 컬럼 누락

FIELD_NAME_ENUM_TYPES['GOODS_TYPE_CD'] = 'GOODS_TYPE_CD'
FIELD_NAME_ENUM_TYPES['KIT_TYPE_CD'] = 'KIT_TYPE_CD'
FIELD_NAME_ENUM_TYPES['BANNER_TYPE_CD'] = 'BANNER_TYPE_CD'
FIELD_NAME_ENUM_TYPES['SALE_STATE_CD'] = 'SALE_STATE_CD'

FIELD_NAME_ENUM_TYPES['QUESTN_TYPE_CD'] = 'QUESTN_TYPE_CD'
FIELD_NAME_ENUM_TYPES['ITEM_TYPE_CD'] = 'ITEM_TYPE_CD'

FIELD_NAME_ENUM_TYPES['INQUIRY_ANSWER_TYPE_CD'] = 'INQUIRY_ANSWER_TYPE_CD'


######################################################################################



