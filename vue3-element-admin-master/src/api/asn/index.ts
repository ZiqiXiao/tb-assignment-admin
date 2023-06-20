import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import {AsnInfoVO, AsnInfoQuery, AsnForm, AsnInfoOpVO, AsnInfoOpQuery} from './types';

/**
 * 任务信息展示
 *
 * @param queryParams
 */
export function getAsnInfo(queryParams?: AsnInfoQuery): AxiosPromise<AsnInfoVO[]> {
	return request({
		url: '/api/v1/asn-disp/disp',
		method: 'get',
		params: queryParams
	});
}

/**
 * 任务信息展示
 *
 * @param queryParams
 */
export function getAsnInfoOp(queryParams?: AsnInfoOpQuery): AxiosPromise<AsnInfoOpVO[]> {
	return request({
		url: '/api/v1/asn-op/disp',
		method: 'get',
		params: queryParams
	});
}

/**
 * 获取任务详情
 *
 * @param id
 */
export function getAsnForm(id: number): AxiosPromise<AsnForm> {
	return request({
		url: '/api/v1/asn-op/' + id + '/form',
		method: 'get'
	});
}

/**
 * 添加任务
 *
 * @param data
 */
export function addAsn(data: AsnForm) {
	return request({
		url: '/api/v1/asn-op/add',
		method: 'post',
		data: data
	});
}

/**
 * 更新任务
 *
 * @param id
 * @param data
 */
export function updateAsn(id: number, data: AsnForm) {
	return request({
		url: '/api/v1/asn-op/update/' + id,
		method: 'put',
		data: data
	});
}

/**
 * 导出任务信息
 *
 * @param queryParams
 * @returns
 */
export function exportAsn(queryParams: AsnInfoQuery) {
	return request({
		url: '/api/v1/asn-op/_export',
		method: 'get',
		params: queryParams,
		responseType: 'arraybuffer'
	});
}
