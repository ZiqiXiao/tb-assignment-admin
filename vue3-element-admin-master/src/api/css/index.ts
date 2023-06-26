import {AxiosPromise} from "axios";
import {CssCode, MaxAsnNo} from "@/api/css/types";
import request from "@/utils/request";

/**
 * 获取客服编码
 *
 * @param cssId
 */
export function getCssCode(cssId: number): AxiosPromise<CssCode> {
  return request({
    url: '/api/v1/css/' + cssId,
    method: 'get'
  });
}

/**
 * 获取客服最新订单
 *
 * @param cssId
 */
export function getMaxAsnNo(cssId: number): AxiosPromise<String> {
  return request({
    url: '/api/v1/css/max-asn-no/' + cssId,
    method: 'get'
  });
}