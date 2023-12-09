import React from 'react';

const SuggestComponent = ({suggestion}) => {
    return (
        <div className='md:w-1/2 flex items-center  bg-pink-100 rounded m-3 p-8 '>
          {
            suggestion? 
            <div className='flex center mx-auto my-auto'>
              <p >{suggestion}</p>
            </div> :
            <div className='flex center mx-auto mx-auto my-auto'>
              <p >👈 👀</p>
            </div>
          }
        </div>
    )
}

export default SuggestComponent;