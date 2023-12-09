import React from 'react';
import { DatePicker, Button, Form } from 'antd';
import {data} from './dataset'


const DateForm = ({setSuggestion}) => {
    const onFinish = (fieldsValue) => {
        const values = {
            ...fieldsValue,
            'date-picker': fieldsValue['date-picker'].format('YYYY-MM-DD'),
        };
        const date = new Date(values['date-picker']).getMonth()
        let result= ""
        // result = data[date]['date-suggestions'][(Math.floor(Math.random() * data[date]['date-suggestions'].length))] 
        switch(date) {
            case 0:
                result = data[date]['date-suggestions'][(Math.floor(Math.random() * data[date]['date-suggestions'].length))] 
                break

            default:
                result = data[0]['date-suggestions'][(Math.floor(Math.random() * data[0]['date-suggestions'].length))] 
                break
        }
    setSuggestion(result);
    return result
};

return (
    <div className='md:w-1/2 m-3 p-8 '>
        <div>
            <h3 className='text-xl'>Hello you</h3>
        </div>
        <Form 
            onFinish={onFinish}
            >
            <Form.Item name="date-picker" id='date-picker' required label = {
                <p className='m-0'>When is your date 😉?</p>
            }> 
                <DatePicker className=''/>
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit" className='bg-indigo-950' >
                Submit
                </Button>
            </Form.Item>
        </Form>
    </div>
)
}

export default DateForm